package com.github.h4ste.umls.uti;

import com.github.h4ste.umls.SemanticType;
import com.github.h4ste.umls.UmlsAtom;
import com.github.h4ste.umls.UmlsConcept;
import com.github.h4ste.umls.UmlsLanguage;
import com.github.h4ste.umls.UmlsService;
import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;
import retrofit2.Retrofit;

/**
 * Helper class for interacting with the UMLS web API
 */
public class RestUmlsService implements UmlsService {

  // Initialize last ticket to be really, really old
  private static volatile LocalDateTime ticketExpiriationTime = null;
  private static volatile String ticketGrantingTicket = null;
  protected final String version;
  private final String apiKey = null;
  private final Retrofit retrofit;
  private final UmlsRestApi api;

  /**
   * Create a new UMLS rest client for the given base URL and the UMLS version
   *
   * @param url base (root) URL of the UMLS api
   * @param version version of UMLS to use (e.g., 2018AA or current)
   */
  public RestUmlsService(String url, String version) {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addCallAdapterFactory(ReactorCallAdapterFactory.createAsync())
        .build();

    this.api = this.retrofit.create(UmlsRestApi.class);
    this.version = version;
  }

  private <K, L extends Iterable<? extends K>, O extends JsonPagedResult<L>>
  Flux<K> flattenPaginatedResults(
      final PageRequestFunction<K, L, O> pageGenerator) {
    return Flux.generate(
        () -> 0,
        (Integer i, SynchronousSink<L> sink) ->
            getTicket()
                .flatMap(t -> pageGenerator.apply(i, t))
                .doOnSuccess(
                    (results) -> {
                      if (results.isLastPage()) {
                        sink.complete();
                      } else {
                        sink.next(results.getResult());
                      }
                    }
                )
                .map(r -> i + 1)
                .block()
    ).concatMap(Flux::fromIterable);
  }

  /**
   * Obtain a single-use service ticket and, if needed, refresh the ticket-granting ticket
   *
   * @return single-use service ticket
   */
  private synchronized Mono<String> getTicket() {
    final LocalDateTime now = LocalDateTime.now();
    if (ticketExpiriationTime == null || ticketExpiriationTime.isBefore(now)) {
      // get a new ticket-granting ticket
      ticketGrantingTicket = api.getTicketGrantingTicketByApiKey(apiKey).block();
      // our new tgt expires in 8 hours!
      ticketExpiriationTime = now.plus(8, ChronoUnit.HOURS);
    }
    return api.getSingleUseServiceTicket(ticketGrantingTicket);
  }

  /**
   * Search for the given term in UMLS
   *
   * @param term plain text search term
   * @return SearchQuery builder
   * @see SearchQuery
   */
  public SearchQuery newSearch(String term) {
    return new SearchQuery(version);
  }


  @FunctionalInterface
  private interface PageRequestFunction<K, L extends Iterable<? extends K>, O extends JsonPagedResult<L>> {
    Mono<O> apply(int page, String ticket);
  }

  private abstract class PagedQuery<T extends PagedQuery<T>> {
    int pageNumber = 1;
    int pageSize = 25;

    public T setPageNumber(int pageNumber) {
      this.pageNumber = pageNumber;
      return (T) this;
    }

    public T setPageSize(int pageSize) {
      this.pageSize = pageSize;
      return (T) this;
    }
  }

  public class SearchQuery extends PagedQuery<SearchQuery> {

    final String term;
    UmlsSearchInputType inputType;
    boolean includeObsolete;
    boolean includeSuppressible;
    UmlsSearchReturnIdType returnIdType;
    Set<UmlsSourceVocabulary> sourceVocabularies;
    UmlsSearchType searchType;

    private SearchQuery(String term) {
      this.term = term;
    }

    public SearchQuery setInputType(UmlsSearchInputType inputType) {
      this.inputType = inputType;
      return this;
    }

    public SearchQuery setIncludeObsolete(boolean includeObsolete) {
      this.includeObsolete = includeObsolete;
      return this;
    }

    public SearchQuery setIncludeSuppressible(boolean includeSuppressible) {
      this.includeSuppressible = includeSuppressible;
      return this;
    }

    public SearchQuery setReturnIdType(UmlsSearchReturnIdType returnIdType) {
      this.returnIdType = returnIdType;
      return this;
    }

    public SearchQuery setSourceVocabularies(Set<UmlsSourceVocabulary> sourceVocabularies) {
      this.sourceVocabularies = sourceVocabularies;
      return this;
    }

    public SearchQuery setSearchType(UmlsSearchType searchType) {
      this.searchType = searchType;
      return this;
    }

    public Flux<JsonSearchResult> fetch() {
      return flattenPaginatedResults(
          (i, ticket) -> api.search(version, ticket, term, inputType,
              includeObsolete, includeSuppressible, returnIdType, sourceVocabularies, searchType,
              pageNumber + i, pageSize)
      );
    }
  }

  class AtomQuery {

    private Set<UmlsSourceVocabulary> sourceVocabularies;
    private Set<UmlsTermTypes> termTypes;
    private UmlsLanguage language;
    private boolean includeObsolete;
    private boolean includeSuppressible;
    private int pageNumber;
    private int pageSize;

    private AtomQuery() {

    }

    public void setSourceVocabularies(Set<UmlsSourceVocabulary> sourceVocabularies) {
      this.sourceVocabularies = sourceVocabularies;
    }

    public void setTermTypes(Set<UmlsTermTypes> termTypes) {
      this.termTypes = termTypes;
    }

    public void setLanguage(UmlsLanguage language) {
      this.language = language;
    }

    public void setIncludeObsolete(boolean includeObsolete) {
      this.includeObsolete = includeObsolete;
    }

    public void setIncludeSuppressible(boolean includeSuppressible) {
      this.includeSuppressible = includeSuppressible;
    }

    public void setPageNumber(int pageNumber) {
      this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
    }
  }

  private static class SemanticTypeImpl implements SemanticType {

    public SemanticTypeImpl(JsonSemanticType jst) {
    }
  }

  private class UmlsConceptImpl implements UmlsConcept {
    private final String name;
    private final String cui;

    private final Set<SemanticType> semanticTypes;

    private UmlsConceptImpl(JsonConcept json) {
      this.name = json.getName();
      this.cui = json.getUi();

      this.semanticTypes = json.getSemanticTypes()
          .stream()
          .map(SemanticTypeImpl::new)
          .collect(Collectors.toSet());
    }

    @Override
    public String getCui() {
      return this.cui;
    }

    @Override
    public String getPreferredName() {
      return this.name;
    }

    @Override
    public Stream<SemanticType> semanticTypes() {
      return semanticTypes.stream();
    }

    @Override
    public Set<SemanticType> getSemanticTypes() {
      return this.semanticTypes;
    }

    @Override
    public Stream<UmlsConcept> broadeningConcepts() {
      return api.getRelations(this, bleh).map();
    }

    @Override
    public Stream<UmlsConcept> narrowingConcepts() {
      return null;
    }

    @Override
    public Stream<UmlsAtom> atoms() {
      return null;
    }
  }


  @Override
  public Stream<UmlsConcept> concepts(String term) {
    return newSearch(term)
        .setReturnIdType(UmlsSearchReturnIdType.CONCEPT)
        .fetch()
        .flatMap(
          srr -> getTicket().flatMap(tkt -> api.getConceptByCui(this.version, srr.getUi(), tkt))
        )
        .map(JsonPagedResult::getResult)
        .map()
        .toStream();
  }

  @Override
  public Stream<String> cuis(String term) {
    return search(term)
        .setReturnIdType(UmlsSearchReturnIdType.CONCEPT)
        .fetch()
        .map(JsonSearchResult::getId)
        .toStream();
  }

  public Stream<UmlsConcept> getNarrowing(UmlsConcept concept) {
    return concept.narrowingConcepts();

  }
}

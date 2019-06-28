package com.github.h4ste.umls.uti;

import com.github.h4ste.umls.UmlsLanguage;
import java.util.List;
import java.util.Set;
import reactor.core.publisher.Mono;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UmlsRestApi {

  @POST("/cas/v1/api-key")
  Mono<String> getTicketGrantingTicketByApiKey(String apiKey);

  @POST("/cas/v1/tickets")
  Mono<String> getTicketGrantingTicketByUsernamePassword(String username, String password);

  @POST("/cas/v1/tickets/{tgt}")
  Mono<String> getSingleUseServiceTicket(@Path("tgt") final String tgt);

  @GET("/search/{version}")
  Mono<JsonPagedResult<JsonSearchResults<JsonSearchResult>>> search(@Path("version") final String version,
      final String ticket,
      @Query("string") final String query,
      UmlsSearchInputType inputType,
      boolean includeObsolete,
      boolean includeSuppressible,
      UmlsSearchReturnIdType returnIdType,
      Set<UmlsSourceVocabulary> sourceVocabularies,
      UmlsSearchType searchType,
      int pageNumber,
      int pageSize);

  @GET("/content/{version}/CUI/{cui}")
  Mono<JsonPagedResult<JsonConcept>> getConceptByCui(@Path("version") String version,
      @Path("cui") String cui,
      String ticket);

  @GET("/content/{version}/CUI/{cui}/atoms")
  Mono<JsonPagedResult<List<JsonAtom>>> getAtomsByCui(@Path("version") String version,
      @Path("cui") String cui,
      String ticket,
      @Query("sabs") Set<UmlsSourceVocabulary> sourceVocabularies,
      @Query("ttys") Set<UmlsTermTypes> termTypes,
      UmlsLanguage language,
      boolean includeObsolete,
      boolean includeSuppressible,
      int pageNumber,
      int pageSize);

  @GET("/content/{version}/CUI/{cui}/atoms/preferred")
  Mono<JsonPagedResult<List<JsonAtom>>> getPreferredAtomByCui(@Path("version") String version,
      @Path("cui") String cui,
      String ticket,
      @Query("sabs") Set<UmlsSourceVocabulary> sourceVocabularies,
      @Query("ttys") Set<UmlsTermTypes> termTypes,
      UmlsLanguage language,
      boolean includeObsolete,
      boolean includeSuppressible,
      int pageNumber,
      int pageSize);

  @GET("/content/{version}/source/{source}/{cui}/atoms")
  Mono<JsonPagedResult<List<JsonAtom>>> getAtomsBySourceConcept(@Path("version") String version,
      @Path("source") String source,
      @Path("cui") String cui,
      String ticket,
      @Query("sabs") Set<UmlsSourceVocabulary> sourceVocabularies,
      @Query("ttys") Set<UmlsTermTypes> termTypes,
      UmlsLanguage language,
      boolean includeObsolete,
      boolean includeSuppressible,
      int pageNumber,
      int pageSize);

  @GET("/content/{version}/source/{source}/{cui}/atoms/preferred")
  Mono<JsonPagedResult<JsonAtom>> getPreferredAtomBySourceCui(@Path("version") String version,
      @Path("source") String source,
      @Path("cui") String cui,
      String ticket,
      @Query("sabs") Set<UmlsSourceVocabulary> sourceVocabularies,
      @Query("ttys") Set<UmlsTermTypes> termTypes,
      UmlsLanguage language,
      boolean includeObsolete,
      boolean includeSuppressible,
      int pageNumber,
      int pageSize);

  @GET("/content/{version}/AUI/{aui}")
  Mono<JsonPagedResult<JsonAtom>> getAtomByAui(@Path("version") String version,
      @Path("aui") String aui,
      String ticket,
      @Query("sabs") Set<UmlsSourceVocabulary> sourceVocabularies,
      @Query("ttys") Set<UmlsTermTypes> termTypes,
      UmlsLanguage language,
      boolean includeObsolete,
      boolean includeSuppressible,
      int pageNumber,
      int pageSize);
}

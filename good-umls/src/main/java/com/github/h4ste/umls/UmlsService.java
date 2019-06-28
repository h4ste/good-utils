package com.github.h4ste.umls;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Interface for accessing UMLS information
 */
public interface UmlsService {

  Stream<UmlsConcept> concepts(String term);
  default List<UmlsConcept> search(String term) {
    return concepts(term).collect(Collectors.toList());
  }

  UmlsConcept getConcept(String cui);

  UmlsAtom getAtom(String aui);
}

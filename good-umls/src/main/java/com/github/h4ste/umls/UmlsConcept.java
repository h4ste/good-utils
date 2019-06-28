package com.github.h4ste.umls;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface UmlsConcept {
  String getCui();
  String getPreferredName();

  Stream<SemanticType> semanticTypes();
  Stream<UmlsConcept> broadeningConcepts();
  Stream<UmlsConcept> narrowingConcepts();
  Stream<UmlsAtom> atoms();

  default Set<SemanticType> getSemanticTypes() {
    return semanticTypes().collect(Collectors.toSet());
  }

  default Set<UmlsConcept> getBroadeningConcepts() {
    return broadeningConcepts().collect(Collectors.toSet());
  }

  default Set<UmlsConcept> getNarrowingConcepts() {
    return narrowingConcepts().collect(Collectors.toSet());
  }

  default Set<UmlsAtom> getAtoms() {
    return atoms().collect(Collectors.toSet());
  }
}

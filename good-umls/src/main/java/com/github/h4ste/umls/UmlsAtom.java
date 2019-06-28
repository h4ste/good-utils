package com.github.h4ste.umls;

public interface UmlsAtom {
  String getAui();
  UmlsConcept getConcept();
  String getCode();
  String getSource();
  String getName();
  UmlsLanguage getLanguage();
}

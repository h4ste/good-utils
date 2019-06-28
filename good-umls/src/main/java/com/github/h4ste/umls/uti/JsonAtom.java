package com.github.h4ste.umls.uti;

public class JsonAtom implements JsonBaseSearchResult{
  private final String ui;
  private final String name;
  private final String termType;
  private final String language;
  private final String rootSource;
  private final String code;
  private final String sourceConcept;
  private final String sourceDescriptor;

  public JsonAtom(String ui, String name, String termType, String language,
      String rootSource, String code, String sourceConcept, String sourceDescriptor) {
    this.ui = ui;
    this.name = name;
    this.termType = termType;
    this.language = language;
    this.rootSource = rootSource;
    this.code = code;
    this.sourceConcept = sourceConcept;
    this.sourceDescriptor = sourceDescriptor;
  }

  @Override
  public String getUi() {
    return ui;
  }

  @Override
  public String getName() {
    return name;
  }

  public String getTermType() {
    return termType;
  }

  public String getLanguage() {
    return language;
  }

  public String getRootSource() {
    return rootSource;
  }

  public String getCode() {
    return code;
  }

  public String getSourceConcept() {
    return sourceConcept;
  }

  public String getSourceDescriptor() {
    return sourceDescriptor;
  }
}

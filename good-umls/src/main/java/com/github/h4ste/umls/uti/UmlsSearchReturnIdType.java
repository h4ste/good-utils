package com.github.h4ste.umls.uti;

public enum UmlsSearchReturnIdType implements RestParameterValue {
  AUI("aui"),
  CONCEPT("concept"),
  CODE("code"),
  SOURCE_CONCEPT("sourceConcept"),
  SOURCE_DESCRIPTOR("sourceDescriptor"),
  SOURCE_UI("sourceUi");

  private final String parameterValue;

  UmlsSearchReturnIdType(String parameterValue) {
    this.parameterValue = parameterValue;
  }

  @Override
  public String getParameterValue() {
    return parameterValue;
  }
}

package com.github.h4ste.umls.uti;

public enum UmlsSearchInputType implements RestParameterValue {
  ATOM("atom"),
  CODE("code"),
  SOURCE_CONCEPT("sourceConcept"),
  SOURCE_DESCRIPTOR("sourceDescriptor"),
  SOURCE_UI("sourceUi"),
  TTY("tty");

  private final String parameterValue;

  UmlsSearchInputType(String parameterValue) {
    this.parameterValue = parameterValue;
  }

  @Override
  public String getParameterValue() {
    return parameterValue;
  }
}

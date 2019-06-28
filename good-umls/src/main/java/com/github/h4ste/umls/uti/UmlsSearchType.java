package com.github.h4ste.umls.uti;

/**
 * Enumerate valid UMLS search types in the UTI web API
 * From: https://documentation.uts.nlm.nih.gov/rest/search/
 * Use ‘exact’ when using inputType = ‘code’, ‘sourceConcept’, ‘sourceDescriptor’, or ‘sourceUi’.
 * For the ‘normalizedWords’ search type, separate each word using a pipe (|) character.
 */
public enum UmlsSearchType implements RestParameterValue {
  EXACT("exact"),
  WORDS("words"),
  TRUNCATE_LEFT("leftTruncation"),
  TRUNCATE_RIGHT("rightTruncation"),
  APPROXIMATE("approximate"),
  NORMALIZED_STRING("normalizedString"),
  NORMALIZED_WORDS("normalizedWords");

  private final String parameterValue;

  UmlsSearchType(String parameterValue) {
    this.parameterValue = parameterValue;
  }

  @Override
  public String getParameterValue() {
    return parameterValue;
  }


}

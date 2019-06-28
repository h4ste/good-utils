package com.github.h4ste.umls.uti;

public class JsonConceptRelation implements JsonBaseSearchResult {

  private final String ui;
  private final String rootSource;
  private final String relationLabel;
  private final String relatedId;
  private final String relatedName;

  public JsonConceptRelation(String ui, String rootSource, String relationLabel,
      String relatedId, String relatedName) {
    this.ui = ui;
    this.rootSource = rootSource;
    this.relationLabel = relationLabel;
    this.relatedId = relatedId;
    this.relatedName = relatedName;
  }

  @Override
  public String getUi() {
    return ui;
  }

  public String getRootSource() {
    return rootSource;
  }

  public String getRelationLabel() {
    return relationLabel;
  }

  public String getRelatedId() {
    return relatedId;
  }

  public String getRelatedName() {
    return relatedName;
  }

  @Override
  public String getName() {
    return null;
  }
}

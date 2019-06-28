package com.github.h4ste.umls.uti;

public class JsonSemanticType implements JsonBaseSearchResult {
  private final String abbreviation;
  private final String ui;
  private final String definition;
  private final String example;
  private final String usageNote;
  private final String treeNumber;

  private final JsonSemanticTypeGroup semanticTypeGroup;

  private final int childCount;

  private final String name;

  public JsonSemanticType(String abbreviation, String ui, String definition, String example,
      String usageNote, String treeNumber,
      JsonSemanticTypeGroup semanticTypeGroup, int childCount, String name) {
    this.abbreviation = abbreviation;
    this.ui = ui;
    this.definition = definition;
    this.example = example;
    this.usageNote = usageNote;
    this.treeNumber = treeNumber;
    this.semanticTypeGroup = semanticTypeGroup;
    this.childCount = childCount;
    this.name = name;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  @Override
  public String getUi() {
    return ui;
  }

  public String getDefinition() {
    return definition;
  }

  public String getExample() {
    return example;
  }

  public String getUsageNote() {
    return usageNote;
  }

  public String getTreeNumber() {
    return treeNumber;
  }

  public JsonSemanticTypeGroup getSemanticTypeGroup() {
    return semanticTypeGroup;
  }

  public int getChildCount() {
    return childCount;
  }

  @Override
  public String getName() {
    return name;
  }

  public static class JsonSemanticTypeGroup {
    private final String abbreviation;
    private final String expandedForm;
    private final int semanticTypeCount;

    public JsonSemanticTypeGroup(String abbreviation, String expandedForm, int semanticTypeCount) {
      this.abbreviation = abbreviation;
      this.expandedForm = expandedForm;
      this.semanticTypeCount = semanticTypeCount;
    }

    public String getAbbreviation() {
      return abbreviation;
    }

    public String getExpandedForm() {
      return expandedForm;
    }

    public int getSemanticTypeCount() {
      return semanticTypeCount;
    }
  }

}

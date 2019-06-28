package com.github.h4ste.umls.uti;

import java.util.HashMap;
import java.util.List;

/*
@JsonIgnoreProperties({"classType","dateAdded","majorRevisionDate","status","attributeCount","cvMemberCount","suppressible","relationCount"})
*/

public class JsonConcept implements JsonBaseSearchResult {
  private final String ui;
  private final List<JsonSemanticType> semanticTypes;
  private final int atomCount;
  private final int relationCount;

  private final String atoms;
  private final String definitions;
  private final String relations;

  private final String name;


  public JsonConcept(String ui, List<JsonSemanticType> semanticTypes, int atomCount,
      int relationCount, String atoms, String definitions, String relations, String name) {
    this.ui = ui;
    this.semanticTypes = semanticTypes;
    this.atomCount = atomCount;
    this.relationCount = relationCount;
    this.atoms = atoms;
    this.definitions = definitions;
    this.relations = relations;
    this.name = name;
  }

  @Override
  public String getUi() {
    return ui;
  }

  public List<JsonSemanticType> getSemanticTypes() {
    return semanticTypes;
  }

  public int getAtomCount() {
    return atomCount;
  }

  public int getRelationCount() {
    return relationCount;
  }

  public String getAtoms() {
    return atoms;
  }

  public String getDefinitions() {
    return definitions;
  }

  public String getRelations() {
    return relations;
  }

  @Override
  public String getName() {
    return name;
  }
}

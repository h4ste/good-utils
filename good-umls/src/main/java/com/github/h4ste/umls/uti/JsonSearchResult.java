package com.github.h4ste.umls.uti;

import java.net.URI;

public class JsonSearchResult implements JsonBaseSearchResult {
  private final String ui;
  private final String name;
  private final String rootSource;

  public JsonSearchResult(String ui, String name, String rootSource) {
    this.ui = ui;
    this.name = name;
    this.rootSource = rootSource;
  }

  @Override
  public String getUi() {
    return ui;
  }

  @Override
  public String getName() {
    return name;
  }

  public String getSource() {
    return rootSource;
  }
}

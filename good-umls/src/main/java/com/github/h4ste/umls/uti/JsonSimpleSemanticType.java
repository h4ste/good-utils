package com.github.h4ste.umls.uti;

public class JsonSimpleSemanticType {
  final String name;
  final String uri;

  public JsonSimpleSemanticType(String name, String uri) {
    this.name = name;
    this.uri = uri;
  }


  public String getName() {
    return name;
  }

  public String getUri() {
    return uri;
  }

  public String getTui() {
    return uri.substring(uri.lastIndexOf('/'));
  }
}

package com.github.h4ste.umls;

public enum UmlsLanguage {
  BAQ("Basque"),
  CHI("Chinese"),
  CZE("Czech"),
  DAN("Danish"),
  DUT("Dutch"),
  ENG("English"),
  EST("Estonian"),
  FIN("Finnish"),
  FRE("French"),
  GER("German"),
  GRE("Greek"),
  HEB("Hebrew"),
  HUN("Hungarian"),
  ITA("Italian"),
  JPN("Japanese"),
  KOR("Korean"),
  LAV("Latvian"),
  NOR("Norwegian"),
  POL("Polish"),
  POR("Portuguese"),
  RUS("Russian"),
  SCR("Croatian"),
  SPA("Spanish"),
  SWE("Swedish"),
  TUR("Turkish");

  private final String description;

  UmlsLanguage(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}

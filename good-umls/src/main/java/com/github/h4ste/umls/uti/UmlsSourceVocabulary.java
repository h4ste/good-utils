package com.github.h4ste.umls.uti;

public enum UmlsSourceVocabulary implements RestParameterValue {
  AIR("AIR"), // AI/RHEUM
  ALT("ALT"), // Alternative Billing Concepts
  AOD("AOD"), // Alcohol and Other Drug Thesaurus
  AOT("AOT"), // Authorized Osteopathic Thesaurus
  ATC("ATC"), // Anatomical Therapeutic Chemical Classification System
  BI("BI"), // Beth Israel Problem List
  CCC("CCC"), // Clinical Care Classification
  CCPSS("CCPSS"), // Clinical Problem Statements
  CCS_10("CCS_10"), // Clinical Classifications Software 10
  CCS("CCS"), // Clinical Classifications Software
  CDT("CDT"), // Current Dental Terminology
  CHV("CHV"), // Consumer Health Vocabulary
  COSTAR("COSTAR"), // COSTAR
  CPM("CPM"), // Medical Entities Dictionary
  CPTSP("CPTSP"), // Current Procedural Terminology Spanish
  CPT("CPT"), // Current Procedural Terminology
  CSP("CSP"), // CRISP Thesaurus
  CST("CST"), // COSTART
  CVX("CVX"), // Vaccines Administered
  DDB("DDB"), // Diseases Database
  DMDICD10("DMDICD10"), // ICD-10 German
  DMDUMD("DMDUMD"), // UMDNS German
  DRUGBANK("DRUGBANK"), // DrugBank
  DSM_5("DSM-5"), // Diagnostic and Statistical Manual of Mental Disorders, Fifth Edition
  DXP("DXP"), // DXplain
  FMA("FMA"), // Foundational Model of Anatomy
  GO("GO"), // Gene Ontology
  GS("GS"), // Gold Standard Drug Database
  HCDT("HCDT"), // Current Dental Terminology in HCPCS
  HCPCS("HCPCS"), // Healthcare Common Procedure Coding System
  HCPT("HCPT"), // CPT in HCPCS
  HGNC("HGNC"), // HUGO Gene Nomenclature Committee
  HL7V2_5("HL7V2.5"), // HL7 Version 2.5
  HL7V3_0("HL7V3.0"), // HL7 Version 3.0
  HLREL("HLREL"), // ICPC2E ICD10 Relationships
  HPO("HPO"), // Human Phenotype Ontology
  ICD10AE("ICD10AE"), // ICD-10, American English Equivalents
  ICD10AMAE("ICD10AMAE"), // ICD-10, Australian Modification, Americanized English Equivalents
  ICD10AM("ICD10AM"), // ICD-10, Australian Modification
  ICD10CM("ICD10CM"), // International Classification of Diseases, Tenth Revision, Clinical Modification
  ICD10DUT("ICD10DUT"), // ICD10, Dutch Translation
  ICD10PCS("ICD10PCS"), // ICD-10 Procedure Coding System
  ICD10("ICD10"), // International Classification of Diseases and Related Health Problems, Tenth Revision
  ICD9CM("ICD9CM"), // International Classification of Diseases, Ninth Revision, Clinical Modification
  ICF_CY("ICF-CY"), // International Classification of Functioning, Disability and Health for Children and Youth
  ICF("ICF"), // International Classification of Functioning, Disability and Health
  ICNP("ICNP"), // International Classification for Nursing Practice
  ICPC2EDUT("ICPC2EDUT"), // ICPC2E Dutch
  ICPC2EENG("ICPC2EENG"), // International Classification of Primary Care, 2nd Edition, Electronic
  ICPC2ICD10DUT("ICPC2ICD10DUT"), // ICPC2-ICD10 Thesaurus, Dutch Translation
  ICPC2ICD10ENG("ICPC2ICD10ENG"), // ICPC2-ICD10 Thesaurus
  ICPC2P("ICPC2P"), // ICPC-2 PLUS
  ICPCBAQ("ICPCBAQ"), // ICPC Basque
  ICPCDAN("ICPCDAN"), // ICPC Danish
  ICPCDUT("ICPCDUT"), // ICPC Dutch
  ICPCFIN("ICPCFIN"), // ICPC Finnish
  ICPCFRE("ICPCFRE"), // ICPC French
  ICPCGER("ICPCGER"), // ICPC German
  ICPCHEB("ICPCHEB"), // ICPC Hebrew
  ICPCHUN("ICPCHUN"), // ICPC Hungarian
  ICPCITA("ICPCITA"), // ICPC Italian
  ICPCNOR("ICPCNOR"), // ICPC Norwegian
  ICPCPOR("ICPCPOR"), // ICPC Portuguese
  ICPCSPA("ICPCSPA"), // ICPC Spanish
  ICPCSWE("ICPCSWE"), // ICPC Swedish
  ICPC("ICPC"), // International Classification of Primary Care
  JABL("JABL"), // Congenital Mental Retardation Syndromes
  KCD5("KCD5"), // Korean Standard Classification of Disease Version 5
  LCH_NW("LCH_NW"), // Library of Congress Subject Headings, Northwestern University subset
  LCH("LCH"), // Library of Congress Subject Headings
  LNC_DE_AT("LNC-DE-AT"), // LOINC Linguistic Variant - German, Austria
  LNC_DE_CH("LNC-DE-CH"), // LOINC Linguistic Variant - German, Switzerland
  LNC_DE_DE("LNC-DE-DE"), // LOINC Linguistic Variant - German, Germany
  LNC_ES_AR("LNC-ES-AR"), // LOINC Linguistic Variant - Spanish, Argentina
  LNC_ES_CH("LNC-ES-CH"), // LOINC Linguistic Variant - Spanish, Switzerland
  LNC_ES_ES("LNC-ES-ES"), // LOINC Linguistic Variant - Spanish, Spain
  LNC_EL_GR("LNC-EL-GR"), // LOINC Linguistic Variant - Greek, Greece
  LNC_ET_EE("LNC-ET-EE"), // LOINC Linguistic Variant - Estonian, Estonia
  LNC_FR_BE("LNC-FR-BE"), // LOINC Linguistic Variant - French, Belgium
  LNC_FR_CA("LNC-FR-CA"), // LOINC Linguistic Variant - French, Canada
  LNC_FR_CH("LNC-FR-CH"), // LOINC Linguistic Variant - French, Switzerland
  LNC_FR_FR("LNC-FR-FR"), // LOINC Linguistic Variant - French, France
  LNC_IT_CH("LNC-IT-CH"), // LOINC Linguistic Variant - Italian, Switzerland
  LNC_IT_IT("LNC-IT-IT"), // LOINC Linguistic Variant - Italian, Italy
  LNC_KO_KR("LNC-KO-KR"), // LOINC Linguistic Variant - Korea, Korean
  LNC_NL_NL("LNC-NL-NL"), // LOINC Linguistic Variant - Dutch, Netherlands
  LNC_PT_BR("LNC-PT-BR"), // LOINC Linguistic Variant - Portuguese, Brazil
  LNC_RU_RU("LNC-RU-RU"), // LOINC Linguistic Variant - Russian, Russia
  LNC_TR_TR("LNC-TR-TR"), // LOINC Linguistic Variant - Turkish, Turkey
  LNC_ZH_CN("LNC-ZH-CN"), // LOINC Linguistic Variant - Chinese, China
  LNC("LNC"), // LOINC
  MCM("MCM"), // Glossary of Clinical Epidemiologic Terms
  MDRCZE("MDRCZE"), // MedDRA Czech
  MDRDUT("MDRDUT"), // MedDRA Dutch
  MDRFRE("MDRFRE"), // MedDRA French
  MDRGER("MDRGER"), // MedDRA German
  MDRHUN("MDRHUN"), // MedDRA Hungarian
  MDRITA("MDRITA"), // MedDRA Italian
  MDRJPN("MDRJPN"), // MedDRA Japanese
  MDRPOR("MDRPOR"), // MedDRA Portuguese
  MDRSPA("MDRSPA"), // MedDRA Spanish
  MDR("MDR"), // MedDRA
  MED_RT("MED-RT"), // MED-RT
  MEDCIN("MEDCIN"), // MEDCIN
  MEDLINEPLUS("MEDLINEPLUS"), // MedlinePlus Health Topics
  MMSL("MMSL"), // Multum
  MMX("MMX"), // Micromedex
  MSHCZE("MSHCZE"), // MeSH Czech
  MSHDUT("MSHDUT"), // MeSH Dutch
  MSHFIN("MSHFIN"), // MeSH Finnish
  MSHFRE("MSHFRE"), // MeSH French
  MSHGER("MSHGER"), // MeSH German
  MSHITA("MSHITA"), // MeSH Italian
  MSHJPN("MSHJPN"), // MeSH Japanese
  MSHLAV("MSHLAV"), // MeSH Latvian
  MSHNOR("MSHNOR"), // MeSH Norwegian
  MSHPOL("MSHPOL"), // MeSH Polish
  MSHPOR("MSHPOR"), // MeSH Portuguese
  MSHRUS("MSHRUS"), // MeSH Russian
  MSHSCR("MSHSCR"), // MeSH Croatian
  MSHSPA("MSHSPA"), // MeSH Spanish
  MSHSWE("MSHSWE"), // MeSH Swedish
  MSH("MSH"), // MeSH
  MTHCMSFRF("MTHCMSFRF"), // Metathesaurus CMS Formulary Reference File
  MTHHH("MTHHH"), // HCPCS Hierarchical Terms (UMLS)
  MTHICD9("MTHICD9"), // ICD-9-CM Entry Terms
  MTHICPC2EAE("MTHICPC2EAE"), // ICPC2E American English Equivalents
  MTHICPC2ICD10AE("MTHICPC2ICD10AE"), // ICPC2E-ICD10 Thesaurus, American English Equivalents
  MTHMSTFRE("MTHMSTFRE"), // Minimal Standard Terminology French (UMLS)
  MTHMSTITA("MTHMSTITA"), // Minimal Standard Terminology Italian (UMLS)
  MTHMST("MTHMST"), // Minimal Standard Terminology (UMLS)
  MTHSPL("MTHSPL"), // FDA Structured Product Labels
  MTH("MTH"), // Metathesaurus Names
  MVX("MVX"), // Manufacturers of Vaccines
  NANDA_I("NANDA-I"), // NANDA-I Taxonomy
  NCBI("NCBI"), // NCBI Taxonomy
  NCISEER("NCISEER"), // NCI SEER ICD Mappings
  NCI_BRIDG("NCI_BRIDG"), // Biomedical Research Integrated Domain Group Model
  NCI_BioC("NCI_BioC"), // BioCarta online maps of molecular pathways, adapted for NCI use
  NCI_CDC("NCI_CDC"), // U.S. Centers for Disease Control and Prevention
  NCI_CDISC("NCI_CDISC"), // Clinical Data Interchange Standards Consortium
  NCI_CRCH("NCI_CRCH"), // Cancer Research Center of Hawaii Nutrition Terminology
  NCI_CTCAE("NCI_CTCAE"), // Common Terminology Criteria for Adverse Events
  NCI_CTEP_SDC("NCI_CTEP-SDC"), // Cancer Therapy Evaluation Program - Simple Disease Classification
  NCI_CTRP_SDC("NCI_CTRP-SDC"), // Clinical Trials Reporting Program
  NCI_CareLex("NCI_CareLex"), // Content Archive Resource Exchange Lexicon
  NCI_DCP("NCI_DCP"), // NCI Division of Cancer Prevention Program
  NCI_DICOM("NCI_DICOM"), // Digital Imaging Communications in Medicine
  NCI_DTP("NCI_DTP"), // NCI Developmental Therapeutics Program
  NCI_FDA("NCI_FDA"), // U.S. Food and Drug Administration
  NCI_GAIA("NCI_GAIA"), // Global Alignment of Immunization Safety Assessment in pregnancy
  NCI_GENC("NCI_GENC"), // Geopolitical Entities, Names, and Codes (GENC) Standard Edition 1
  NCI_ICH("NCI_ICH"), // International Conference on Harmonization
  NCI_JAX("NCI_JAX"), // Jackson Laboratories Mouse Terminology, adapted for NCI use
  NCI_KEGG("NCI_KEGG"), // KEGG Pathway Database
  NCI_NCI_GLOSS("NCI_NCI-GLOSS"), // NCI Dictionary of Cancer Terms
  NCI_NCI_HGNC("NCI_NCI-HGNC"), // NCI HUGO Gene Nomenclature
  NCI_NCI_HL7("NCI_NCI-HL7"), // NCI Health Level 7
  NCI_NCPDP("NCI_NCPDP"), // National Council for Prescription Drug Programs
  NCI_NICHD("NCI_NICHD"), // National Institute of Child Health and Human Development
  NCI_PI_RADS("NCI_PI-RADS"), // Prostate Imaging Reporting and Data System
  NCI_PID("NCI_PID"), // National Cancer Institute Nature Pathway Interaction Database
  NCI_RENI("NCI_RENI"), // Registry Nomenclature Information System
  NCI_UCUM("NCI_UCUM"), // Unified Code for Units of Measure
  NCI_ZFin("NCI_ZFin"), // Zebrafish Model Organism Database
  NCI("NCI"), // NCI Thesaurus
  NDDF("NDDF"), // FDB MedKnowledge
  NDFRT_FDASPL("NDFRT_FDASPL"), // National Drug File - FDASPL
  NDFRT_FMTSME("NDFRT_FMTSME"), // National Drug File - FMTSME
  NDFRT("NDFRT"), // National Drug File - Reference Terminology
  NEU("NEU"), // Neuronames Brain Hierarchy
  NIC("NIC"), // Nursing Interventions Classification
  NOC("NOC"), // Nursing Outcomes Classification
  NUCCPT("NUCCPT"), // National Uniform Claim Committee - Health Care Provider Taxonomy
  OMIM("OMIM"), // Online Mendelian Inheritance in Man
  OMS("OMS"), // Omaha System
  PCDS("PCDS"), // Patient Care Data Set
  PDQ("PDQ"), // Physician Data Query
  PNDS("PNDS"), // Perioperative Nursing Data Set
  PPAC("PPAC"), // Pharmacy Practice Activity Classification
  PSY("PSY"), // Psychological Index Terms
  QMR("QMR"), // Quick Medical Reference
  RAM("RAM"), // Clinical Concepts by R A Miller
  RCDAE("RCDAE"), // Read Codes Am Engl
  RCDSA("RCDSA"), // Read Codes Am Synth
  RCDSY("RCDSY"), // Read Codes Synth
  RCD("RCD"), // Read Codes
  RXNORM("RXNORM"), // RXNORM
  SCTSPA("SCTSPA"), // SNOMED CT Spanish Edition
  SNMI("SNMI"), // SNOMED Intl 1998
  SNM("SNM"), // SNOMED 1982
  SNOMEDCT_US("SNOMEDCT_US"), // US Edition of SNOMED CT
  SNOMEDCT_VET("SNOMEDCT_VET"), // Veterinary Extension to SNOMED CT
  SOP("SOP"), // Source of Payment Typology
  SPN("SPN"), // Standard Product Nomenclature
  SRC("SRC"), // Source Terminology Names (UMLS)
  TKMT("TKMT"), // Traditional Korean Medical Terms
  ULT("ULT"), // UltraSTAR
  UMD("UMD"), // Universal Medical Device Nomenclature System
  USPMG("USPMG"), // USP Model Guidelines
  UWDA("UWDA"), // Digital Anatomist
  VANDF("VANDF"), // National Drug File
  WHOFRE("WHOFRE"), // WHOART French
  WHOGER("WHOGER"), // WHOART German
  WHOPOR("WHOPOR"), // WHOART Portuguese
  WHOSPA("WHOSPA"), // WHOART Spanish
  WHO("WHO"); // WHOART

  private final String parameterValue;

  UmlsSourceVocabulary(String parameterValue) {
    this.parameterValue = parameterValue;
  }

  @Override
  public String getParameterValue() {
    return parameterValue;
  }
}

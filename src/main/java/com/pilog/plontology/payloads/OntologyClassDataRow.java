package com.pilog.plontology.payloads;

import lombok.Data;

@Data
public class OntologyClassDataRow
{
    private String sourceCode;
    private String uscVersion;
    private String itemRef;
    private String definition;
    private String term;
    private String abbreviation;
    private String action;
    private String object;
    private String qualifier;
    private String catType;
    private String drID;
    private String termID;
    private String definitionID;
    private String abbreviationID;
    private String drTitle;
    private String conceptID;
    private String languageID;
    private String activeInd;
    private OntologyPropertyDataRow[] properties;
}

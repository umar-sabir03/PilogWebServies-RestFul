package com.pilog.plontology.payloads;

import lombok.Data;

@Data
public class PreferenceOntologyDataRow {
    private String orgGUID;

    private String langaugeID;

    private String preferredTermID;

    private String preferredDefinitionID;

    private String preferredAbbreviationID;

    private String conceptID;

    private String typeRC;

    private String conceptType;

    private String Language;

    private String term;

    private String definition;

    private String abbreviation;

    private String conceptTypeID;

    private String catType;

    private String locale;

    private String uom;

    private String unspscCode;

    private String unspscDesc;
}

package com.pilog.plontology.payloads;

import lombok.Data;

import java.io.Serializable;

@Data
public class OntologyDataRow implements Serializable
{

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

}

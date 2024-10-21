package com.pilog.plontology.payloads;

import lombok.Data;

@Data
public class DataRequirementDataRow {
    private String orgID;

    private String languageID;

    private String classConceptID;

    private String propertyConceptID;

    private String itemRef;

    private String itemSeq;

    private String sequencePOD;

    private String sequenceSFD;

    private String inclPOD;

    private String inclSFD;

    private String valueMandatoryInd;

    private String dudeMandatoryInd;

    private String dataTypeRef;

    private String activeInd;

    private String placeInd;

    private String spaceInd;

    private String preferredTermID;

    private String preferredDefinitionID;

    private String preferredAbbreviationID;

    private String descriptor;
    private String property;
}

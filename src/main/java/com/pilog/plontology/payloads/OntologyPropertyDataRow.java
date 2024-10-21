package com.pilog.plontology.payloads;

import lombok.Data;

@Data
public class OntologyPropertyDataRow
{
    private String sourceCode;
    private String uscVersion;
    private String itemRef;
    private Integer itemSeq;
    private String definition;
    private String term;
    private String abbreviation;
    private String termID;
    private String definitionID;
    private String abbreviationID;
    private String conceptID;
    private String languageID;
    private String activeInd;
    private String placeInd;
    private String spaceInd;
    private String purchaseText;
    private String shortText;
    private Integer purchaseSequence;
    private Integer shortSequence;
    private String mandatoryInd;
    private Integer dataTypeRef;
    private String inclUOMinSFD;
    private String dataType;

}

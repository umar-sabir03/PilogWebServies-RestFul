package com.pilog.plontology.payloads;

import lombok.Data;

@Data
public class GenerateTermDTO {
    private String crrId;
    private String statusRc;
    private String statusReason;
    private String conceptTypeId;
    private String orgId;
    private String languageId;
    private String conceptId;
    private String odtId;
    private String conceptOdtId;
    private String termId;
    private String term;
    private String termOriginatorRef;
    private String definitionId;
    private String definition;
    private String definitionOriginatorRef;
    private String abbreviationId;
    private String abbreviation;
    private String abbreviationOriginatorRef;
    private String labelId;
    private String label;
    private String labelOriginatorRef;
    private String sysCreatedBy;
    private String sysCreatedOn;
    private String sysUpdatedBy;
    private String sysUpdatedOn;
    private String oldLanguageId;
    private String oldDefinition;
    private String oldTerm;
    private String comments;
    private String oldAbbreviation;
    private String oldLabel;
    private String originatingOrgId;
    private String drId;
    private String classId;
    private String propertyId;
    private String irdi;
    private String domain;
    private String languageIrdi;
    private String guidelines;
    private String oldGuidelines;
    private Long totalCount;
    private String message;
    private String messageFlag;

}

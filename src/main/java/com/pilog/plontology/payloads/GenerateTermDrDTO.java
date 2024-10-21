package com.pilog.plontology.payloads;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GenerateTermDrDTO {
    private String orgId;
    private String drId;
    private String sequenceExtNo; // Changed to camelCase
    private String conceptType;
    private String classConceptId;
    private String classTermId;
    private String classTerm;
    private String classAbbr;
    private String domain;
    private String propertyConceptId;
    private String propertyTerm;
    private String propertyTermId;
    private String propertyAbbr;
    private String definitionId;
    private String definition;
    private String statusReason;
    private String txnmyStatus;
    private String shortSeq;
    private String longSeq;
    private String requiredFlag;
    private String pdrFlag;
    private String dataType;
    private String drivenBy;
    private String languageId;
    private String attachId;
    private LocalDate createDate;
    private String createBy;
    private LocalDate editDate;
    private String editBy;
    private LocalDate approveDate;
    private String approveBy;
    private LocalDate processedDate;
    private String processedBy;
    private String highLevelId;
    private String stxtFlag;
    private String ltxtFlag;
    private String locale;
    private String activeFlag;
    private String auditId;
    private String comments;
    private String message;
    private String messageFlag;
    private Integer totalCount;
    private String copyFlag;
    private String approveFlag;
}

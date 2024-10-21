package com.pilog.plontology.payloads;

import lombok.Data;

import java.util.Date;
@Data
public class RefreshCharValuesRequestDTO {
    private String recordNo;
    private String region;
    private String locale;
    private String classConceptId;
    private String propertyConceptId;
    private String propertyName;
    private String propValueConceptId;
    private String propertyValue1;
    private String propertyValue2;
    private String propUomConceptId;
    private String propertyUom;
    private String dataType;
    private String shortSeq;
    private String longSeq;
    private String requiredFlag;
    private String stxtFlag;
    private String ltxtFlag;
    private Date createDate;
    private String createBy;
    private Date editDate;
    private String editBy;
    private String auditId;
    private String languageId;
    private String propertyAbbr;
    private String definition;
    private String highLevelFlag;
    private String splitValue;
    private Date charColumn1Date;
    private Date charColumn2Date;
    private Date charColumn3Date;
    private Date charColumn4Date;
    private String charColumn5;
    private String charColumn6;
    private String charColumn7;
    private String charColumn8;
    private String charColumn9;
    private String charColumn10;
    private String charColumn11;
    private String charColumn12;
    private String charColumn13;
    private String charColumn14;
    private String charColumn15;
}

package com.pilog.plontology.model.pprm;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "O_RECORD_CHAR")
@Data
public class RecordChar {

    @Id
    @Column(name = "RECORD_NO", length = 20, nullable = false)
    private String recordNo;

    @Column(name = "REGION", length = 20, nullable = false)
    private String region;

    @Column(name = "LOCALE", length = 5, nullable = false)
    private String locale;

    @Column(name = "CLASS_CONCEPT_ID", length = 30, nullable = false)
    private String classConceptId;

    @Column(name = "PROPERTY_CONCEPT_ID", length = 30, nullable = false)
    private String propertyConceptId;

    @Column(name = "PROPERTY_NAME", length = 250, nullable = false)
    private String propertyName;

    @Column(name = "PROP_VALUE_CONCEPT_ID", length = 30)
    private String propValueConceptId;

    @Column(name = "PROPERTY_VALUE1", length = 4000)
    private String propertyValue1;

    @Column(name = "PROPERTY_VALUE2", length = 1500)
    private String propertyValue2;

    @Column(name = "PROP_UOM_CONCEPT_ID", length = 30)
    private String propUomConceptId;

    @Column(name = "PROPERTY_UOM", length = 50)
    private String propertyUom;

    @Column(name = "DATA_TYPE", length = 30, nullable = false)
    private String dataType;

    @Column(name = "SHORT_SEQ", nullable = false)
    private Integer shortSeq;

    @Column(name = "LONG_SEQ", nullable = false)
    private Integer longSeq;

    @Column(name = "REQUIRED_FLAG", length = 1, nullable = false)
    private String requiredFlag;

    @Column(name = "STXT_FLAG", length = 1, nullable = false)
    private String stxtFlag;

    @Column(name = "LTXT_FLAG", length = 1, nullable = false)
    private String ltxtFlag;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDate createDate;

    @Column(name = "CREATE_BY", length = 50, nullable = false)
    private String createBy;

    @Column(name = "EDIT_DATE")
    private LocalDate editDate;

    @Column(name = "EDIT_BY", length = 50)
    private String editBy;

    @Column(name = "AUDIT_ID", length = 60)
    private String auditId;

    @Column(name = "PROPERTY_ABBR", length = 50)
    private String propertyAbbr;

    @Column(name = "LANGUAGE_ID", length = 30)
    private String languageId;

    @Column(name = "DEFINITION", length = 4000)
    private String definition;

    @Column(name = "HIGH_LEVEL_FLAG", length = 1)
    private String highLevelFlag;

    @Column(name = "SPLIT_VALUE", length = 100)
    private String splitValue;

    @Column(name = "CHAR_COLUMN1_DATE")
    private LocalDate charColumn1Date;

    @Column(name = "CHAR_COLUMN2_DATE")
    private LocalDate charColumn2Date;

    @Column(name = "CHAR_COLUMN3_DATE")
    private LocalDate charColumn3Date;

    @Column(name = "CHAR_COLUMN4_DATE")
    private LocalDate charColumn4Date;

    @Column(name = "CHAR_COLUMN5", length = 4000)
    private String charColumn5;

    @Column(name = "CHAR_COLUMN6", length = 4000)
    private String charColumn6;

    @Column(name = "CHAR_COLUMN7", length = 4000)
    private String charColumn7;

    @Column(name = "CHAR_COLUMN8", length = 4000)
    private String charColumn8;

    @Column(name = "CHAR_COLUMN9", length = 4000)
    private String charColumn9;

    @Column(name = "CHAR_COLUMN10", length = 4000)
    private String charColumn10;

    @Column(name = "CHAR_COLUMN11", length = 4000)
    private String charColumn11;

    @Column(name = "CHAR_COLUMN12", length = 4000)
    private String charColumn12;

    @Column(name = "CHAR_COLUMN13", length = 4000)
    private String charColumn13;

    @Column(name = "CHAR_COLUMN14", length = 4000)
    private String charColumn14;

    @Column(name = "CHAR_COLUMN15", length = 4000)
    private String charColumn15;

}

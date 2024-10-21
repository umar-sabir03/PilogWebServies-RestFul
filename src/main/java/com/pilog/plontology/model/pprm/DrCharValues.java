package com.pilog.plontology.model.pprm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DR_CHAR_VALUES")
public class DrCharValues {

    @EmbeddedId
    private DrCharValuesId id;

    @Column(name = "ABRREVIATION_ID", length = 30)
    private String abbreviationId;

    @Column(name = "ABRREVIATION", length = 250)
    private String abbreviation;

    @Column(name = "DATA_TYPE", length = 30, nullable = false)
    private String dataType;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDate createDate;

    @Column(name = "CREATE_BY", length = 50, nullable = false)
    private String createBy;

    @Column(name = "EDIT_DATE")
    private LocalDate editDate;

    @Column(name = "EDIT_BY", length = 50)
    private String editBy;

    @Column(name = "DR_ID", columnDefinition = "RAW", nullable = false)
    private String drId;

    @Column(name = "TERM2", length = 1500)
    private String term2;

    @Column(name = "ACTIVE_IND", length = 1)
    private String activeInd;

    @Column(name = "AUDIT_ID", length = 100)
    private String auditId;

    @Column(name = "PROPERTY_CONCEPT_IDS", length = 4000)
    private String propertyConceptIds;

    @Column(name = "PROPERTY_VALUES", length = 4000)
    private String propertyValues;

    @Column(name = "UOM_ID", length = 30)
    private String uomId;

    @Column(name = "LANGUAGE", length = 50,nullable = false)
    private String language;
}

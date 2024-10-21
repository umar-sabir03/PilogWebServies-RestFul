package com.pilog.plontology.model.apexqa;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CONCEPT_REGISTRATION_REQUEST")
@Data
public class ConceptRegistrationRequest {

    @Id
    @Column(name = "CRR_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crr_seq")
    @SequenceGenerator(name = "crr_seq", sequenceName = "SEQ_CNCPT_REG", allocationSize = 1)
    private Long crrId;

    @Column(name = "STATUS_RC", length = 100)
    private String statusRc;

    @Column(name = "STATUS_REASON", length = 4000)
    private String statusReason;

    @Column(name = "CONCEPT_TYPE_ID", columnDefinition = "RAW(16)")
    private String conceptTypeId;

    @Column(name = "ORG_ID", columnDefinition = "RAW(16)")
    private String orgId;

    @Column(name = "LANGUAGE_ID", columnDefinition = "RAW(16)")
    private String languageId;

    @Column(name = "CONCEPT_ID", columnDefinition = "RAW(16)")
    private String conceptId;

    @Column(name = "ODT_ID", columnDefinition = "RAW(16)")
    private String odtId;

    @Column(name = "CONCEPT_ODT_ID", columnDefinition = "RAW(16)")
    private String conceptOdtId;

    @Column(name = "TERM_ID", columnDefinition = "RAW(16)")
    private String termId;

    @Column(name = "TERM", length = 4000)
    private String term;

    @Column(name = "TERM_ORIGINATOR_REF", length = 1000)
    private String termOriginatorRef;

    @Column(name = "DEFINITION_ID", columnDefinition = "RAW(16)")
    private String definitionId;

    @Column(name = "DEFINITION", length = 4000)
    private String definition;

    @Column(name = "DEFINITION_ORIGINATOR_REF", length = 1000)
    private String definitionOriginatorRef;

    @Column(name = "ABBREVIATION_ID", columnDefinition = "RAW(16)")
    private String abbreviationId;

    @Column(name = "ABBREVIATION", length = 4000)
    private String abbreviation;

    @Column(name = "ABBREVIATION_ORIGINATOR_REF", length = 1000)
    private String abbreviationOriginatorRef;

    @Column(name = "LABEL_ID", columnDefinition = "RAW(16)")
    private String labelId;

    @Column(name = "LABEL", length = 4000)
    private String label;

    @Column(name = "LABEL_ORIGINATOR_REF", length = 1000)
    private String labelOriginatorRef;

    @Column(name = "SYS_CREATED_BY", length = 100)
    private String sysCreatedBy;

    @Column(name = "SYS_CREATED_ON")
    private LocalDateTime sysCreatedOn;

    @Column(name = "SYS_UPDATED_BY", length = 100)
    private String sysUpdatedBy;

    @Column(name = "SYS_UPDATED_ON")
    private LocalDateTime sysUpdatedOn;

    @Column(name = "OLD_LANGUAGE_ID", columnDefinition = "RAW(16)")
    private String oldLanguageId;

    @Column(name = "OLD_DEFINITION", length = 4000)
    private String oldDefinition;

    @Column(name = "OLD_TERM", length = 4000)
    private String oldTerm;

    @Column(name = "COMMENTS", length = 4000)
    private String comments;

    @Column(name = "OLD_ABBREVIATION", length = 4000)
    private String oldAbbreviation;

    @Column(name = "OLD_LABEL", length = 16)
    private String oldLabel;

    @Column(name = "ORIGINATING_ORG_ID", length = 2000)
    private String originatingOrgId;

    @Column(name = "DR_ID", columnDefinition = "RAW(16)")
    private String drId;

    @Column(name = "CLASS_ID", columnDefinition = "RAW(16)")
    private String classId;

    @Column(name = "PROPERTY_ID", columnDefinition = "RAW(16)")
    private String propertyId;

    @Column(name = "IRDI", length = 290)
    private String irdi;

    @Column(name = "DOMAIN", length = 50)
    private String domain;

    @Column(name = "LANGUAGE_IRDI", length = 290)
    private String languageIrdi;

    @Column(name = "GUIDELINES", length = 4000)
    private String guidelines;

    @Column(name = "OLD_GUIDELINES", length = 4000)
    private String oldGuidelines;

    @Column(name = "REQUEST_FROM", length = 4000)
    private String requestFrom;

    @Column(name = "ABBREVATION", length = 255)
    private String abbrevation;

    @Column(name = "ABBREVATION_ORIGIN_REF", length = 255)
    private String abbrevationOriginRef;

    @Column(name = "DEFINITION_ORIGIN_REF", length = 255)
    private String definitionOriginRef;

    @Column(name = "LANGUAGEIRD1", length = 255)
    private String languageIrd1;

    @Column(name = "ORGN_ID", length = 255)
    private String orgnId;

    @Column(name = "TERM_ORIGIN_REF", length = 255)
    private String termOriginRef;

}

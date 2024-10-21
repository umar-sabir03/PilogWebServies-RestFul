package com.pilog.plontology.model.apex;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "MTRL_PPO_TERM_ADVANCED")
public class MtrlPpoTermAdvanced {

    @Id
    @Column(name = "CONCEPT_ID", length = 290, nullable = false)
    private String conceptId;

    @Column(name = "CLASS", length = 500, nullable = false)
    private String clazz;

    @Column(name = "OBJECT", length = 500, nullable = false)
    private String object;

    @Column(name = "QUALIFIER", length = 2000, nullable = false)
    private String qualifier;

    @Column(name = "CONCEPT_TYPE", length = 8, nullable = false)
    private String conceptType;

    @Column(name = "ABBREVIATION", length = 500, nullable = false)
    private String abbreviation;

    @Column(name = "DEFINITION", length = 4000, nullable = false)
    private String definition;

    @Column(name = "DOMAIN", length = 50, nullable = false)
    private String domain;

    @Column(name = "INDUSTRY", length = 1000, nullable = false)
    private String industry;

    @Column(name = "DISCIPLINE", length = 1000, nullable = false)
    private String discipline;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "LOCALE", length = 101, nullable = false)
    private String locale;

    @Column(name = "RAW_LANGUAGE_ID", columnDefinition = "RAW(16)", nullable = true)
    private byte[] rawLanguageId;

    @Column(name = "LANGUAGE_ID", length = 290, nullable = false)
    private String languageId;

    @Column(name = "UNSPSC_CODE", length = 8, nullable = false)
    private String unspscCode;

    @Column(name = "UNSPSC_DESCR", length = 4000, nullable = false)
    private String unspscDescr;

    @Column(name = "IMG_IND", length = 8, nullable = false)
    private String imgInd;

    @Column(name = "ORG_ID", columnDefinition = "RAW(16)", nullable = true)
    private byte[] orgId;
}
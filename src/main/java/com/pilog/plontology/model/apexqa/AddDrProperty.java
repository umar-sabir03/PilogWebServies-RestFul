package com.pilog.plontology.model.apexqa;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "ADD_DR_PROPERTY")
@Data
public class AddDrProperty {


    @Column(name = "PROPERTY_ID", length = 50, nullable = false)
    private String propertyId;

    @Column(name = "STATUS_RC", length = 100, nullable = false)
    private String statusRc;

    @Column(name = "STATUS_REASON", length = 4000, nullable = false)
    private String statusReason;
    @Id
    @Column(name = "DR_ID", length = 50, nullable = false)
    private String drId;

    @Column(name = "CLASS_ID", length = 50, nullable = false)
    private String classId;

    @Column(name = "LANGUAGE_ID", length = 50, nullable = false)
    private String languageId;

    @Column(name = "ORG_ID", length = 50, nullable = false)
    private String orgnId;

    @Column(name = "ORIGINATING_ORG_ID", length = 50, nullable = false)
    private String originatingOrgId;

    @Column(name = "REQUIRED", length = 1, nullable = false)
    private String manFlag;

    @Column(name = "PROPERTY_SEQUENCE", nullable = false)
    private Integer propSeq;

    @Column(name = "DEFINITION", length = 4000, nullable = false)
    private String definition;

    @Column(name = "DATA_TYPE", length = 200, nullable = false)
    private String dataType;

    @Column(name = "PROPERTY_NAME", length = 100, nullable = false)
    private String propName;

    @Column(name = "PROCESS_IND", length = 1, nullable = false)
    private String propInd;

    @Column(name = "CREATE_DATE", nullable = false)
    private ZonedDateTime createDate;

    @Column(name = "CREATE_USER", length = 100, nullable = false)
    private String sysCreatedBy;

    @Column(name = "ORG_NAME", length = 20, nullable = false)
    private String orgName;

    @Column(name = "IND_PPO", length = 1, nullable = false)
    private String indPpo;

}

package com.pilog.plontology.model.apex;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ORG_STRUCTURE")
@Data
public class OrgStructure {

    @Id
    @Column(name = "ORG_ID")
    private String orgId;

    @Column(name = "PARENT_ORG_ID")
    private byte[] parentOrgId; // Assuming RAW is byte[]

    @Column(name = "NAME", length = 500)
    private String name;

    @Column(name = "ODS_ID")
    private byte[] odsId; // Assuming RAW is byte[]

    @Column(name = "RESPONSE_TIME")
    private BigDecimal responseTime;

    @Column(name = "RESPONSE_TIME_UOM", length = 10)
    private String responseTimeUom;

    @Column(name = "SLA_DURATION", length = 100)
    private String slaDuration;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "FREE_MATERIAL_CLASSES")
    private BigDecimal freeMaterialClasses;

    @Column(name = "FREE_SERVICE_CLASSES")
    private BigDecimal freeServiceClasses;

    @Column(name = "FREE_MATERIAL_PROPERTIES")
    private BigDecimal freeMaterialProperties;

    @Column(name = "FREE_SERVICE_PROPERTIES")
    private BigDecimal freeServiceProperties;

    @Column(name = "FREE_UOMS")
    private BigDecimal freeUoms;

    @Column(name = "FREE_CHANGES_EXISTING_MAT_DR")
    private BigDecimal freeChangesExistingMatDr;

    @Column(name = "FREE_CHANGES_EXISTING_SERV_DR")
    private BigDecimal freeChangesExistingServDr;

    @Column(name = "FREE_DR_CHANGES_MATERIAL")
    private BigDecimal freeDrChangesMaterial;

    @Column(name = "FREE_DR_CHANGES_SERVICE")
    private BigDecimal freeDrChangesService;

    @Column(name = "ACTIVE_IND", length = 1)
    private String activeInd;
}

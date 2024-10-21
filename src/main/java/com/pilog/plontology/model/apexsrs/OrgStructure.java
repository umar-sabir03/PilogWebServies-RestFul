package com.pilog.plontology.model.apexsrs;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORG_STRUCTURE")
@Data
public class OrgStructure {
    @Id
    @Column(name = "ORG_ID", nullable = false)
    private String orgId;

    @Column(name = "PARENT_ORG_ID", nullable = true)
    private String parentOrgId;

    @Column(name = "NAME", length = 500, nullable = false)
    private String name;

    @Column(name = "ORG_SUPP_TYPE", nullable = true)
    private Integer orgSuppType;

    @Column(name = "ACTIVE_ID", length = 1, nullable = false)
    private String activeId;
}

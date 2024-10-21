package com.pilog.plontology.model.pprm;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORGN_STRUCTURE")
@Data
public class OrgnStructure {

    @Id
    @Column(name = "ORGN_ID", columnDefinition = "RAW")
    private String orgnId;

    @Column(name = "NAME", length = 25)
    private String name;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name = "CREATE_BY", length = 50)
    private String createBy;

    @Column(name = "EDIT_DATE")
    @Temporal(TemporalType.DATE)
    private Date editDate;

    @Column(name = "EDIT_BY", length = 50)
    private String editBy;

    @Column(name = "ERP", length = 50)
    private String erp;

    @Column(name = "HIGH_LEVEL_ORGN_ID", columnDefinition = "RAW")
    private byte[] highLevelOrgnId;

    @Column(name = "ORGN_TYPE", length = 20)
    private String orgnType;

    @Column(name = "ACTIVE_FLAG", length = 1)
    private String activeFlag;

    @Column(name = "R_QL", length = 100)
    private String rQl;

    @Column(name = "R_TL", length = 100)
    private String rTl;

    @Column(name = "R_ST", length = 1)
    private String rSt;

    @Column(name = "AUDIT_ID", length = 100)
    private String auditId;

}
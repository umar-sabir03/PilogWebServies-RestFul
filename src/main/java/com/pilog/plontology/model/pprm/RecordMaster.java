package com.pilog.plontology.model.pprm;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "MV_O_RECORD_MASTER")
@Data
public class RecordMaster {

    @Id
    @Column(name = "RECORD_NO", length = 20, nullable = false)
    private String recordNo;

    @Column(name = "ERP_NO", length = 20)
    private String erpNo;

    @Column(name = "DR_ID1", columnDefinition = "RAW", nullable = false)
    private byte[] drId1;

    @Column(name = "DR_ID2", columnDefinition = "RAW")
    private byte[] drId2;

    @Column(name = "DR_ID3", columnDefinition = "RAW")
    private byte[] drId3;

    @Column(name = "DR_ID4", columnDefinition = "RAW")
    private byte[] drId4;

    @Column(name = "DR_ID5", columnDefinition = "RAW")
    private byte[] drId5;

    @Column(name = "RECORD_TYPE", length = 4, nullable = false)
    private String recordType;

    @Column(name = "RECORD_GROUP", length = 8, nullable = false)
    private String recordGroup;

    @Column(name = "UOM", length = 10, nullable = false)
    private String uom;

    @Column(name = "REGION", length = 20, nullable = false)
    private String region;

    @Column(name = "LOCALE", length = 5, nullable = false)
    private String locale;

    @Column(name = "SHORT_DESC", length = 240)
    private String shortDesc;

    @Column(name = "DATA_SOURCE", length = 50)
    private String dataSource;

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

    @Column(name = "STATUS", length = 40)
    private String status;

    @Column(name = "CLASS_TERM", length = 250)
    private String classTerm;

    @Column(name = "DATA_SOURCE1", length = 4)
    private String dataSource1;

}

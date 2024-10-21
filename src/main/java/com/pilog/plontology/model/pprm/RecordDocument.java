package com.pilog.plontology.model.pprm;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "MV_O_RECORD_DOCUMENT")
@Data
public class RecordDocument {

    @Id
    @Column(name = "RECORD_NO", length = 20, nullable = false)
    private String recordNo;

    @Column(name = "REGION", length = 20, nullable = false)
    private String region;

    @Column(name = "LOCALE", length = 5, nullable = false)
    private String locale;

    @Column(name = "BUSINESS_UNIT", length = 20, nullable = false)
    private String businessUnit;

    @Column(name = "INSTANCE", length = 2, nullable = false)
    private String instance;

    @Column(name = "DOCUMENT_NO", length = 100, nullable = false)
    private String documentNo;

    @Column(name = "DOCUMENT_TYPE", length = 20, nullable = false)
    private String documentType;

    @Column(name = "REVISION", length = 1, nullable = false)
    private String revision;

    @Column(name = "DOCUMENT_ITEM", length = 1, nullable = false)
    private String documentItem;

    @Column(name = "ITEM_POSITION", length = 1, nullable = false)
    private String itemPosition;

    @Column(name = "VENDOR_ID", length = 20, nullable = false)
    private String vendorId;

    @Column(name = "VENDOR_NAME", length = 250, nullable = false)
    private String vendorName;

    @Column(name = "STXT_FLAG", length = 1)
    private String stxtFlag;

    @Column(name = "LTXT_FLAG", length = 1)
    private String ltxtFlag;

    @Column(name = "STRIP_DOCUMENT_NO", length = 100)
    private String stripDocumentNo;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDate createDate;

    @Column(name = "CREATE_BY", length = 50, nullable = false)
    private String createBy;

    @Column(name = "EDIT_DATE")
    private LocalDate editDate;

    @Column(name = "EDIT_BY", length = 50)
    private String editBy;

    @Column(name = "AUDIT_ID", length = 60, nullable = false)
    private String auditId;

    @Column(name = "DATA_SOURCE", length = 4, nullable = false)
    private String dataSource;

}


package com.pilog.plontology.model.pprm;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "MV_O_RECORD_REFERENCE")
@Data
public class RecordReference {

    @Id
    @Column(name = "RECORD_NO", length = 20, nullable = false)
    private String recordNo;

    @Column(name = "REGION", length = 20, nullable = false)
    private String region;

    @Column(name = "LOCALE", length = 5, nullable = false)
    private String locale;

    @Column(name = "REFERENCE_NO", length = 100, nullable = false)
    private String referenceNo;

    @Column(name = "REFERENCE_TYPE", length = 20, nullable = false)
    private String referenceType;

    @Column(name = "VENDOR_ID", length = 20, nullable = false)
    private String vendorId;

    @Column(name = "VENDOR_NAME", length = 250, nullable = false)
    private String vendorName;

    @Column(name = "BRAND_NAME", length = 250, nullable = false)
    private String brandName;

    @Column(name = "STRIP_REFERENCE_NO", length = 100)
    private String stripReferenceNo;

    @Column(name = "SUPERSEDED_REFERENCE", length = 100)
    private String supersededReference;

    @Column(name = "STXT_FLAG", length = 1)
    private String stxtFlag;

    @Column(name = "LTXT_FLAG", length = 1)
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

    @Column(name = "BUSINESS_UNIT", length = 20, nullable = false)
    private String businessUnit;

    @Column(name = "EQUIVALENT_REF_NUMBER", length = 4000, nullable = false)
    private String equivalentRefNumber;

    @Column(name = "ALTERNATE_VENDOR", length = 4000, nullable = false)
    private String alternateVendor;

    @Column(name = "CLASS", length = 250, nullable = false)
    private String classType;

}

package com.pilog.plontology.model.pprm;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MV_SMART_SEARCH_REF")
@Data
public class MvSmartSearchTextRef {

    @Id
    @Column(name = "ORGN_ID", columnDefinition = "RAW")
    private String orgnId;

    @Column(name = "ROW_ID")
    private Long rowId;

    @Column(name = "RECORD_NO", length = 20)
    private String recordNo;

    @Column(name = "ERPSFD", length = 4000)
    private String erpsfd;

    @Column(name = "PURCHASE", length = 4000)
    private String purchase;

    @Column(name ="CLASS", length = 250)
    private String className;

    @Column(name = "LOCALE", length = 5)
    private String locale;

    @Column(name = "REGION", length = 20)
    private String region;

    @Column(name = "UNSPSC_CODE", length = 8)
    private String unspscCode;

    @Column(name = "UNSPSC_DESC", length = 200)
    private String unspscDesc;

    @Column(name = "CONCEPT_ID", length = 30)
    private String conceptId;



    @Column(name = "QUALITY_LEVEL", length = 5)
    private String qualityLevel;

    @Column(name = "TRUST_LEVEL", length = 5)
    private String trustLevel;

    @Column(name = "ACTIVE_STATUS", length = 1)
    private char activeStatus;

    @Column(name = "CREATE_BY", length = 50)
    private String createBy;

    @Column(name = "REFERENCE_NO", length = 1500)
    private String referenceNo;

    @Column(name = "REFERENCE_TYPE", length = 25)
    private String referenceType;

    @Column(name = "VENDOR_NAME", length = 250)
    private String vendorName;

    @Column(name = "STRIP_REFERENCE_NO", length = 1500)
    private String stripReferenceNo;

    @Column(name = "RESEARCH_URL", length = 4000)
    private String researchUrl;

    @Column(name = "UOM", length = 10)
    private String uom;

    @Column(name = "MATL_TYPE", length = 4)
    private String matlType;

    @Column(name = "INDUSTRY", length = 50)
    private String industry;

    @Column(name = "CATEGORY", length = 100)
    private String category;

    @Column(name = "EQUIVALENT_REF_NUMBER", length = 4000)
    private String equivalentRefNumber;

    @Column(name = "ALTERNATE_VENDOR", length = 4000)
    private String alternateVendor;

    @Column(name = "ISIC_CODE", length = 100)
    private String isicCode;

    @Column(name = "ISIC_CODE_DESC", length = 2000)
    private String isicCodeDesc;

    @Column(name = "COMMODITY", length = 100)
    private String commodity;

    @Column(name = "SUB_CATEGORY", length = 100)
    private String subCategory;

    @Override
    public String toString() {
        return "MvSmartSearchTextRef{" +
                "orgnId='" + orgnId + '\'' +
                ", rowId=" + rowId +
                ", recordNo='" + recordNo + '\'' +
                ", erpsfd='" + erpsfd + '\'' +
                ", purchase='" + purchase + '\'' +
                ", className='" + className + '\'' +
                ", locale='" + locale + '\'' +
                ", region='" + region + '\'' +
                ", unspscCode='" + unspscCode + '\'' +
                ", unspscDesc='" + unspscDesc + '\'' +
                ", conceptId='" + conceptId + '\'' +
                ", qualityLevel='" + qualityLevel + '\'' +
                ", trustLevel='" + trustLevel + '\'' +
                ", activeStatus=" + activeStatus +
                ", createBy='" + createBy + '\'' +
                ", referenceNo='" + referenceNo + '\'' +
                ", referenceType='" + referenceType + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", stripReferenceNo='" + stripReferenceNo + '\'' +
                ", researchUrl='" + researchUrl + '\'' +
                ", uom='" + uom + '\'' +
                ", matlType='" + matlType + '\'' +
                ", industry='" + industry + '\'' +
                ", category='" + category + '\'' +
                ", equivalentRefNumber='" + equivalentRefNumber + '\'' +
                ", alternateVendor='" + alternateVendor + '\'' +
                ", isicCode='" + isicCode + '\'' +
                ", isicCodeDesc='" + isicCodeDesc + '\'' +
                ", commodity='" + commodity + '\'' +
                ", subCategory='" + subCategory + '\'' +
                '}';
    }
}

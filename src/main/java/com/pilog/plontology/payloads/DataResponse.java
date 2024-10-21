package com.pilog.plontology.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class DataResponse {
    public String recordNo;
    public String region;
    public String businessUnit;
    public String locale;
    public String type;
    public String text;
    public String instance;
    public String documentNo;
    public String documentType;
    public String revision;
    public String documentItem;
    public String itemPosition;
    public String vendorId;
    public String vendorName;
    public String stxtFlag;
    public String ltxtFlag;
    public String dStxtFlag;
    public String dLtxtFlag;
    public String rStxtFlag;
    public String rLtxtFlag;
    public String stripDocumentNo;
    public String erpNo;
    public String drId1;
    public String drId2;
    public String drid3;
    public String drid4;
    public String drid5;
    public String recordType;
    public String recordGroup;
    public String uom;
    public String shortDesc;
    public String dataSource;
    public String classTerm;
    public String conceptId;
    public String classConceptId;
    public String propertyConceptId;
    public String propertyName;
    public String propValueConceptId;
    public String propertyValue1;
    public String propertyValue2;
    public String propUomConceptId;
    public String propertyUom;
    public String dataType;
    public String shortSeq;
    public String longSeq;
    public String requiredFlag;
    public String languageId;
    public String brandName;
    public String stripReferenceNo;
    public String supersededReference;
    public String refAuditId;
    public String dataSource1;

    public String status;
    public String docAuditId;
    public String referenceNo;
    public String referenceType;
    public String docVendorId;
    public String docVendorName;

}

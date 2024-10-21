package com.pilog.plontology.payloads;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "CATALOG_REF_DATA"
)
public class CatalogRefData {
    String RECORD_NO;
    String REGION;
    String LOCALE;
    String REFERENCE_NO;
    String REFERENCE_TYPE;
    String VENDOR_ID;
    String VENDOR_NAME;
    String BRAND_NAME;
    String STRIP_REFERENCE_NO;
    String SUPERSEDED_REFERENCE;
    String STXT_FLAG;
    String LTXT_FLAG;
    String CREATE_DATE;
    String CREATE_BY;
    String EDIT_DATE;
    String EDIT_BY;
    String AUDIT_ID;
    String BUSINESS_UNIT;
}

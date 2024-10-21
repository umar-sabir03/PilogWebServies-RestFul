package com.pilog.plontology.payloads;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "CATALOG_DOC_DATA"
)
public class CatalogDocData {
    String RECORD_NO;
    String REGION;
    String LOCALE;
    String BUSINESS_UNIT;
    String INSTANCE;
    String DOCUMENT_NO;
    String DOCUMENT_TYPE;
    String REVISION;
    String DOCUMENT_ITEM;
    String ITEM_POSITION;
    String VENDOR_ID;
    String VENDOR_NAME;
    String STXT_FLAG;
    String LTXT_FLAG;
    String CREATE_DATE;
    String CREATE_BY;
    String EDIT_DATE;
    String EDIT_BY;
    String AUDIT_ID;
    String STRIP_DOCUMENT_NO;
}

package com.pilog.plontology.payloads;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "CATALOG_TEXT_DATA"
)
public class CatalogTextData {
        String RECORD_NO;
        String REGION;
        String LOCALE;
        String TYPE;
        String TEXT;
        String CREATE_DATE;
        String CREATE_BY;
        String EDIT_DATE;
        String EDIT_BY;
        String AUDIT_ID;
        String BUSINESS_UNIT;
}

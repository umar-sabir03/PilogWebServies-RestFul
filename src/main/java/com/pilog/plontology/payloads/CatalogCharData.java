package com.pilog.plontology.payloads;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "CATALOG_CHAR_DATA"
)
public class CatalogCharData {
    String RECORD_NO;
    String REGION;
    String LOCALE;
    String CLASS_CONCEPT_ID;
    String PROPERTY_CONCEPT_ID;
    String PROPERTY_NAME;
    String PROP_VALUE_CONCEPT_ID;
    String PROPERTY_VALUE1;
    String PROPERTY_VALUE2;
    String PROP_UOM_CONCEPT_ID;
    String PROPERTY_UOM;
    String DATA_TYPE;
    String SHORT_SEQ;
    String LONG_SEQ;
    String REQUIRED_FLAG;
    String STXT_FLAG;
    String LTXT_FLAG;
    String CREATE_DATE;
    String CREATE_BY;
    String EDIT_DATE;
    String EDIT_BY;
    String AUDIT_ID;
    String PROPERTY_ABBR;
}

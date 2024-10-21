package com.pilog.plontology.payloads;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CATALOG_PROPERTY_TEMPLATE")
public class CatalogPropertyTemplate {

        String ORGN_ID;
        String CONCEPT_ID;
        String CONCEPT_TYPE;
        String TERM_ID;
        String TERM;
        String DEFINITION_ID;
        String ABBREVIATION_ID;
        String ABBREVIATION;
        String LABEL_ID;
        String LABEL;
        String LANGUAGE_ID;
        String LANGUAGE;
        String CREATE_DATE;
}

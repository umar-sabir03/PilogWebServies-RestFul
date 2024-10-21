package com.pilog.plontology.payloads;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "CATALOG_DATA_RESPONSE"
)
public class CatalogDataResponse {
    public List<CatalogCharData> CHARACTERISTICS;
    public List<CatalogDocData> DOCUMENTATION;
    public List<CatalogRefData> REFERENCE;
    public List<CatalogTextData> TEXT;
    public String MESSAGE;
    public String TERM;
    public String RECORD_NO;
    public String CONCEPT_ID;
}
package com.pilog.plontology.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ReferenceERP {

    @JsonProperty("RECORD_NO")
    private String recordNo;

    @JsonProperty("REFERENCE_NO")
    private String referenceNo;

    @JsonProperty("UOM")
    private String uom;

    @JsonProperty("CLASS_TERM")
    private String classTerm;

    @JsonProperty("SHORT_DESC")
    private String shortDesc;

    @JsonProperty("PURCHASE")
    private String purchase;

    @JsonProperty("MATL_GROUP")
    private String matlGroup;

    @JsonProperty("URL")
    private String url;
}

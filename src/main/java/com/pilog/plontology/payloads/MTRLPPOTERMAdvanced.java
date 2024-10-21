package com.pilog.plontology.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MTRLPPOTERMAdvanced {
    @JsonProperty("CLASS")
    public String clazz;
    @JsonProperty("OBJECT")
    public String object;
    @JsonProperty("QUALIFIER")
    public String qualifier;
    @JsonProperty("CONCEPT_ID")
    public String conceptId;
    @JsonProperty("ABBREVIATION")
    public String abbreviation;
    @JsonProperty("DEFINITION")
    public String definition;
    @JsonProperty("DOMAIN")
    public String domain;
    @JsonProperty("INDUSTRY")
    public String industry;
    @JsonProperty("DISCIPLINE")
    public String discipline;
}
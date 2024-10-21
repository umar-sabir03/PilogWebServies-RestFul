package com.pilog.plontology.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MTRLPPOTermSimple
{
    @JsonProperty("CLASS")
    public String clazz;
    @JsonProperty("OBJECT")
    public String object;
    @JsonProperty("QUALIFIER")
    public String qualifier;
    @JsonProperty("CONCEPT_ID")
    public String conceptId;
    @JsonProperty("CONCEPT_TYPE")
    public String conceptType;
    @JsonProperty("LANGUAGE")
    public String language;
    @JsonProperty("CLASS_ABBREVIATION")
    public String classAbbreviation;
}
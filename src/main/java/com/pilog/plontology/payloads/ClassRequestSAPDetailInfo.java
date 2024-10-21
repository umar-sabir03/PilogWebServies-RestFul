package com.pilog.plontology.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClassRequestSAPDetailInfo
{
    @JsonProperty("CLASS")
    public String clazz;
    @JsonProperty("LOCALE")
    public String locale;
    @JsonProperty("CONCEPT_TYPE")
    public String conceptType;
    @JsonProperty("CONCEPT_ID")
    public String conceptId;
}

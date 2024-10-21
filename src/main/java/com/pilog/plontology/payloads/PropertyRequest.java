package com.pilog.plontology.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PropertyRequest{
    @JsonProperty("CONCEPT_ID")
    public String conceptId;
}

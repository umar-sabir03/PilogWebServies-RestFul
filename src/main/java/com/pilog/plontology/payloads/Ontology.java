package com.pilog.plontology.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Ontology {

    @JsonProperty("CLASS")
    private String clazz;

    @JsonProperty("concept_id")
    private String conceptId;

    @JsonProperty("DR_ID")
    private String drId;

    @JsonProperty("O_ABBR")
    private String oAbbr;

    @JsonProperty("UNSPSC_CODE")
    private String unspscCode;

    @JsonProperty("UNSPSC_DESC")
    private String unspscDesc;
}
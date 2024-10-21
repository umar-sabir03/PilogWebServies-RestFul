package com.pilog.plontology.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Property {

    @JsonProperty("ITEM_REF")
    private String itemRef;

    @JsonProperty("WORD")
    private String word;

    @JsonProperty("O_DEF")
    private String definition;

    @JsonProperty("MANDATORY_IND")
    private String mandatoryIndicator;

    @JsonProperty("O_SEQ")
    private String sequence;

    @JsonProperty("O_STXT_SEQ")
    private String shortTextSequence;

    @JsonProperty("O_ABBR")
    private String abbreviation;

    @JsonProperty("DATATYPE_REF")
    private String dataTypeReference;

    @JsonProperty("CONCEPTID")
    private String conceptId;
}

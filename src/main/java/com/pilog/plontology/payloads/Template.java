package com.pilog.plontology.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Template
{
    @JsonProperty("ITEM_REF")
    public String itemRef;
    @JsonProperty("WORD")
    public String word;
    @JsonProperty("O_DEF")
    public String oDef;
    @JsonProperty("MANDATORY_IND")
    public String mandatoryInd;
    @JsonProperty("O_SEQ")
    public String oSeq;
    @JsonProperty("O_STXT_SEQ")
    public String oStxtSeq;
    @JsonProperty("O_ABBR")
    public String oAbbr;
    @JsonProperty("DATATYPE_REF")
    public String datatypeRef;
    @JsonProperty("CONCEPTID")
    public String conceptId;
    @JsonProperty("HIGH_LVL_IND")
    public String highLvlInd;
}
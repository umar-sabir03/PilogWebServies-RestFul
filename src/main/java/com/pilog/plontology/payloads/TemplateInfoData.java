package com.pilog.plontology.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class TemplateInfoData {

        @JsonProperty("itemRef")
        private String itemRef;

        @JsonProperty("word")
        private String word;

        @JsonProperty("oDef")
        private String oDef;

        @JsonProperty("mandatoryInd")
        private String mandatoryInd;

        @JsonProperty("oSeq")
        private String oSeq;

        @JsonProperty("oStxtSeq")
        private String oStxtSeq;

        @JsonProperty("oAbbr")
        private String oAbbr;

        @JsonProperty("datatypeRef")
        private String datatypeRef;

        @JsonProperty("conceptId")
        private String conceptId;

        @JsonProperty("highLvlInd")
        private String highLvlInd;

        @JsonProperty("uom")
        private String uom;

        @JsonProperty("message")
        private String message;

        // Uncomment if you decide to use these fields
        // @JsonProperty("termId")
        // private String termId;

        // @JsonProperty("abbrId")
        // private String abbrId;

        // @JsonProperty("definitionId")
        // private String definitionId;
}

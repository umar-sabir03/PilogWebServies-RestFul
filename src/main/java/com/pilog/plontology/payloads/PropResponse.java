package com.pilog.plontology.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropResponse {

    private String itemRef;
    private String word;
    private String oDef;
    private String mandatoryInd;
    private String oSeq;
    private String oStxtSeq;
    private String oAbbr;
    private String datatypeRef;
    private String conceptId;
    private String highLvlInd;
    private String termId;
    private String definitionId;
    private String abbreviationId;
    private String uom;
}
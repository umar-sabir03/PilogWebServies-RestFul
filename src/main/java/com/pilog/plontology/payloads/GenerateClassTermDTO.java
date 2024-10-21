package com.pilog.plontology.payloads;

import lombok.Data;

@Data
public class GenerateClassTermDTO {
    String conceptTypeId;
    String languageId;
    String orgnId;
    String term;
    String termOriginRef;
    String definition;
    String definitionOriginRef;
    String abbrevation;
    String abbrevationOriginRef;
    String label;
    String labelOriginatorRef;
    String languageIRD1;
    String statusReason;
    String domain;
}

package com.pilog.plontology.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropRequestDTO {

    private String classConceptId;
    private String orgnId;
    private String languageId;
    private String isNested;
}

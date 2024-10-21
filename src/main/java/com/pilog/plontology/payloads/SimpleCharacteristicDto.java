package com.pilog.plontology.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleCharacteristicDto {
    private String clazz;
    private String conceptId;
}
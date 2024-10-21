package com.pilog.plontology.payloads;

import lombok.Data;

import java.util.List;

@Data
public class V10TranslateResponse {
    private List<V10TranslateResponseDTO> translatedWords;
    private String message;
    private String responseCode;
}

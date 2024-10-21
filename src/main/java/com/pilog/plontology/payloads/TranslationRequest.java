package com.pilog.plontology.payloads;

import lombok.Data;

@Data
public class TranslationRequest {
    public String olocale;
    public String term;
    public String orgnId;
}

package com.pilog.plontology.service;

import com.pilog.plontology.payloads.V10TranslateRequest;
import com.pilog.plontology.payloads.V10TranslateResponse;

import java.util.Map;

public interface IV10TranslatorBeanService {
    Map<String, Object> checkTranslations(V10TranslateRequest translateRequest);

    V10TranslateResponse translate(V10TranslateRequest translateRequest);
}

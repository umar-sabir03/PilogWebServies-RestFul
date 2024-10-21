package com.pilog.plontology.service;

import com.pilog.plontology.payloads.V10TranslateRequest;
import com.pilog.plontology.payloads.V10TranslateResponse;

public interface IGenericTranslatorService {

    V10TranslateResponse getGenericTranslator(V10TranslateRequest translateRequest);
}

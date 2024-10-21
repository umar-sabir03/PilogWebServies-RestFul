package com.pilog.plontology.service;

import com.pilog.plontology.payloads.TemplateRequest;
import com.pilog.plontology.payloads.TemplateResponse;

public interface IWelspunSearch {
    TemplateResponse fetchTemplate(TemplateRequest request);
}

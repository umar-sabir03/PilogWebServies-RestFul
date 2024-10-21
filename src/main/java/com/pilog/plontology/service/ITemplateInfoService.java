package com.pilog.plontology.service;

import com.pilog.plontology.payloads.TemplateInfoResponse;

public interface ITemplateInfoService {
    TemplateInfoResponse fetch(String term, String orgId, String isNested);

    TemplateInfoResponse fetchMLData(String term, String orgId, String isNested, String languageId);

    TemplateInfoResponse fetchMLPropswithClassTerm(String term, String orgId, String languageId);
}

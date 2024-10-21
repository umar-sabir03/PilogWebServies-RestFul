package com.pilog.plontology.service;

import com.pilog.plontology.payloads.TemplateRequestDTO;
import com.pilog.plontology.payloads.TemplateSearchResponse;

import java.util.List;

public interface ITemplateSearch {
    List<TemplateSearchResponse> fetchClassTemplate(TemplateRequestDTO request);
}

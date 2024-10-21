package com.pilog.plontology.service;

import com.pilog.plontology.payloads.TemplateRequestDTO;
import com.pilog.plontology.payloads.TemplateResInsertDTO;

public interface ITemplateInsertService {
    TemplateResInsertDTO classTemplateInsert(TemplateRequestDTO request);
}

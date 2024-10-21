package com.pilog.plontology.service;

import com.pilog.plontology.payloads.OTerm;
import com.pilog.plontology.payloads.RequestDto;

import java.util.List;

public interface ISAPTranslationsService {
    List<OTerm> search(RequestDto request);
}

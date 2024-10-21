package com.pilog.plontology.service;

import com.pilog.plontology.payloads.RepResponseDTO;
import com.pilog.plontology.payloads.RepositoryRequestSAPDTO;

public interface IRepSearchService {
     RepResponseDTO searchRepositoryXml(RepositoryRequestSAPDTO request);
}

package com.pilog.plontology.service;

import com.pilog.plontology.payloads.ResponseDTO;

public interface IVisionCatalogService {
    ResponseDTO VisionMasterQuery(String recordNo);

    ResponseDTO VisionDocumentQuery(String recordNo);

    ResponseDTO VisionTextQuery(String recordNo);

    ResponseDTO VisionReferenceQuery(String recordNo);

    ResponseDTO VisionCharQuery(String recordNo);
}

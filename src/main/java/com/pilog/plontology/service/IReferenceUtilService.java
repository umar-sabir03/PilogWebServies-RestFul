package com.pilog.plontology.service;

import com.pilog.plontology.payloads.ReferenceRequest;
import com.pilog.plontology.payloads.ReferenceResponse;

public interface IReferenceUtilService {
     ReferenceResponse partNoInfo(ReferenceRequest request);

}

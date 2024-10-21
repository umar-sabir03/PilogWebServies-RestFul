package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.ReferenceRequest;
import com.pilog.plontology.payloads.ReferenceResponse;
import com.pilog.plontology.service.IReferenceUtilService;
import org.springframework.stereotype.Service;

@Service
public class RefrenceUtilsServiceImpl implements IReferenceUtilService {
    @Override
    public ReferenceResponse partNoInfo(ReferenceRequest request) {
        //  conn = dbConnection.getPartNumberConnection();  pertno. db details not found
        return null;
    }
}

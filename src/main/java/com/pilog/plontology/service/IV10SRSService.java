package com.pilog.plontology.service;

import com.pilog.plontology.payloads.SRSResponseDTO;
import com.pilog.plontology.payloads.V10SRSBean;
import com.pilog.plontology.payloads.V10SRSRequestBean;

import java.util.List;

public interface IV10SRSService {
    List<SRSResponseDTO> getSrsData(V10SRSRequestBean requestBean);

    String createSRSForV10(V10SRSBean v10SRSBean);
}

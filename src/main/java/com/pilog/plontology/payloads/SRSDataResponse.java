package com.pilog.plontology.payloads;

import lombok.Data;

import java.util.List;

@Data
public class SRSDataResponse {
    private List<SRSResponseDTO> repResponseList;

}

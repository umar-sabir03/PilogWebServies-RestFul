package com.pilog.plontology.payloads;

import lombok.Data;

import java.util.List;

@Data
public class RefreshCharValuesRequest {
    private String orgnId;

    private List<RefreshCharValuesRequestDTO> charValuesRequestDTOs;
}

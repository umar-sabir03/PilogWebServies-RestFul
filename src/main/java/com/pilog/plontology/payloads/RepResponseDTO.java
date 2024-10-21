package com.pilog.plontology.payloads;

import lombok.Data;

import java.util.List;

@Data
public class RepResponseDTO {
    public String count;

    private List<RepResponse> repResponseList;
}

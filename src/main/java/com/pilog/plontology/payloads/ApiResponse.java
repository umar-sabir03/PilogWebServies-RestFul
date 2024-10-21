package com.pilog.plontology.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

    private String message;
    private boolean success;

    public ApiResponse(String message, boolean b) {
    }
}
package com.pilog.plontology.payloads;

import lombok.Data;

@Data
public class SRSFileRequestDTO {

    private String fileEncodedString;

    private String fileName;

    private String fileType;

}

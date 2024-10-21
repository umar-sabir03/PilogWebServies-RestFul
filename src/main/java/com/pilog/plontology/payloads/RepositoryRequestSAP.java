package com.pilog.plontology.payloads;

import lombok.Data;

@Data
public class RepositoryRequestSAP {

    private String column;
    private String operator;
    private String value;
    private String datatype;
}

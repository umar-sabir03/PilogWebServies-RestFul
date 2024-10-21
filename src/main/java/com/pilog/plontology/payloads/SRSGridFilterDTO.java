package com.pilog.plontology.payloads;

import lombok.Data;

@Data
public class SRSGridFilterDTO {

    private String filterValue;

    private String filterColumn;

    private String filterCondition;

}

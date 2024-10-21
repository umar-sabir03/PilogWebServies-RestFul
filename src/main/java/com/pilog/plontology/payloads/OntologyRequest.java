package com.pilog.plontology.payloads;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OntologyRequest {

    @ApiModelProperty(value = "Class name", example = "CAMPANA")
    public String clazz;

    @ApiModelProperty(value = "Concept ID", example = "1007-1#01-086039#1")
    public String conceptId;
}
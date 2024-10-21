package com.pilog.plontology.payloads;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MLDataRequestDto {
    @ApiModelProperty(value = "Search term", example = "VIS")
    private String term;
    @ApiModelProperty(value = "Organization ID", example = "EF80F32666E846E0A32187C54AE8F2DB")
    private String orgId;
    @ApiModelProperty(value = "Is Nested", example = "")
    private String isNested;
    private String languageId;
}

package com.pilog.plontology.payloads;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data

public class ImageRequest
{    @ApiModelProperty(value="Term" ,example="IMPINGER")
    public String term;
}

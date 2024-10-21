package com.pilog.plontology.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TemplateRequest
{
    public String classterm;
    public String conceptId;
    @JsonProperty("orgn_id")
    public String orgnId;
}
package com.pilog.plontology.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClassRequesAdvancedInfo
{
    @JsonProperty("CLASS")
    public String clazz;
    @JsonProperty("LOCALE")
    public String locale;
    @JsonProperty("IMAGE")
    public String image;
}


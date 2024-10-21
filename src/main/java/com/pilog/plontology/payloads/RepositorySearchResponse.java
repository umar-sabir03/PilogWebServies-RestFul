package com.pilog.plontology.payloads;

import lombok.Data;

import javax.xml.bind.annotation.XmlTransient;

@Data
@XmlTransient
public class RepositorySearchResponse {
    public String responseData;
    public String message;

}

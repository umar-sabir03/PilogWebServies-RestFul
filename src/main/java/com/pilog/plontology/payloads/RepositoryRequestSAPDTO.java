package com.pilog.plontology.payloads;
import lombok.Data;

import java.util.List;

@Data
public class RepositoryRequestSAPDTO {

    private List<RepositoryRequestSAP> repRequestList ;

    public String recordstartindex;
    public String recordendindex;
    public String orgnId;
    public String locale;
    public String trustLevel;
    public String qualityLevel;

}

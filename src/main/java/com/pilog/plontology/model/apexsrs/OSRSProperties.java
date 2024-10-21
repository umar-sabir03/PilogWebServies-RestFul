package com.pilog.plontology.model.apexsrs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "osrs.request")
@Data
public class OSRSProperties {

    private String typeid;
    private String userrequestid;
    private String statusid;
    private String jcreateuser;
    private String jedituser;
    private String issueid;
    private String appid;
    private String appsupportid;
    private String assignedby;
    private String altassignedby;
    private char processflag;
}

package com.pilog.plontology.payloads;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class V10SRSBean {

    private List<SRSFileRequestDTO> srsFileRequestData;

    private String srsDescription;

    @NotNull(message = "please provide srsorgn")
    private String srsorgn;

    private String v10user;

    private String v10emailid;

    private String criticallity;
}

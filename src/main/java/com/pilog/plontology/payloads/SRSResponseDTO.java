package com.pilog.plontology.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SRSResponseDTO {

    public String srsId;

    public String srsDesc;

    public String requestType;

    public String requestedBy;

    public Date requestedDate;

    public Date endDate;

    public String priority;

    public String status;

    public Date jCreateDate;

    public String jCreateUser;

    public Date JEditDate;

    public String jEditUser;

    public String organisation;

    public String issue;

    public String application;

    public String appSupportId;

    public Character processFlag;

    public String assignedBy;

    public String estimatedDate;

    public String techSupDesc;

    public String closedBy;

    public String assignedTo;

    public Date assignedDate;

    public Date workstartTime;

    public String userComments;

    public String transferBy;

    public Date transferDate;

    public String altAssignedBy;

    public String reqUserMobile;

    public String activateId;

    public String userSupDesc;

    public Date dateSelection;

    public BigInteger noofHours;

    public Date estimatedDate1;

    public BigInteger totalHoursAssg;

    public String clientUserName;

    public String clientEmailId;

    public BigDecimal totalCount;
}

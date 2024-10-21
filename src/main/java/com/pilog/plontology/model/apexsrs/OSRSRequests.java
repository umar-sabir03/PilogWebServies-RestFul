package com.pilog.plontology.model.apexsrs;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "O_SRS_REQUESTS")
@Data
public class OSRSRequests {

    @Id
    @Column(name = "SRS_ID", length = 40)
    private String srsId;

    @Column(name = "SRS_DESC", length = 4000)
    private String srsDesc;

    @Column(name = "REQUEST_TYPE_ID", length = 40)
    private String requestTypeId;

    @Column(name = "USER_REQUEST_ID")
    private String userRequestId;

    @Column(name = "REQUESTED_DATE")
    private Date requestedDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "PRIORITY", length = 30)
    private String priority;

    @Column(name = "STATUS_ID")
    private String statusId;

    @Column(name = "J_CREATE_DATE")
    private Date jCreateDate;

    @Column(name = "J_CREATE_USER", length = 4000)
    private String jCreateUser;

    @Column(name = "J_EDIT_DATE")
    private Date jEditDate;

    @Column(name = "J_EDIT_USER", length = 4000)
    private String jEditUser;

    @Column(name = "ORG_ID")
    private String orgId;


    @Column(name = "ISSUE_ID")
    private String issueId;


    @Column(name = "APP_ID")
    private String appId;


    @Column(name = "APP_SUPPORT_ID")
    private String appSupportId;

    @Column(name = "PROCESS_FLAG", length = 1)
    private char processFlag;

    @Column(name = "ASSIGNED_BY")
    private String assignedBy;

    @Column(name = "ESTIMATED_DATE", length = 100)
    private String estimatedDate;

    @Column(name = "TECH_SUP_DESC", length = 4000)
    private String techSupDesc;

    @Column(name = "ASSIGNED_TO")
    private String assignedTo;

    @Column(name = "ASSIGNED_DATE")
    private Date assignedDate;

    @Column(name = "WORKSTART_TIME")
    private Date workStartTime;

    @Column(name = "USER_COMMENTS", length = 4000)
    private String userComments;


    @Column(name = "TRANSFER_BY")
    private String transferBy;

    @Column(name = "TRANSFER_DATE")
    private Date transferDate;

    @Column(name = "ALT_ASSIGNED_BY", length = 4000)
    private String altAssignedBy;

    @Column(name = "REQ_USER_MOBILE", length = 4000)
    private String reqUserMobile;

    @Column(name = "ACTIVICATE_ID", length = 50)
    private String activicateId;

    @Column(name = "USER_SUP_DESC", length = 4000)
    private String userSupDesc;

    @Column(name = "DATE_SELECTION")
    private Date dateSelection;

    @Column(name = "NO_OF_HOURS")
    private Integer noOfHours;

    @Column(name = "ESTIMATED_DATE1")
    private Date estimatedDate1;

    @Column(name = "TOTAL_HOURS_ASSG")
    private Integer totalHoursAssg;

    @Column(name = "CLIENT_USER_NAME", length = 50)
    private String clientUserName;

    @Column(name = "CLIENT_EMAIL_ID", length = 100)
    private String clientEmailId;

    @Column(name = "TECH_SUP_DATA", length = 50)
    private String techSupData;

    @Column(name = "WORKDONE_COMMENT", length = 4000)
    private String workdoneComment;

    @Column(name = "CLOSED_BY", length = 1000)
    private String closedBy;
}

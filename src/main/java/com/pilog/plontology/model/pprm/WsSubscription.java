package com.pilog.plontology.model.pprm;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "WS_SUBSCRIPTION")
@Data
public class WsSubscription {

    @Id
    @Column(name = "ORGN_ID", columnDefinition = "RAW")
    private String orgnId;

    @Column(name = "WS_ID", length = 100)
    private String wsId;

    @Column(name = "NO_OF_HITS_PER_DAY")
    private Integer noOfHitsPerDay;

    @Column(name = "SUBSCRIPTION_DATE", length = 20)
    private String subscriptionDate;

    @Column(name = "SUBSCRIPTION_EXPIRY_DATE")
    private Date subscriptionExpiryDate;

    @Column(name = "SINGLE_MULTIPLE_FLAG", length = 1)
    private String singleMultipleFlag;

    @Column(name = "MAX_LIMIT")
    private Integer maxLimit;

    @Column(name = "RENEWAL_DATE")
    private Date renewalDate;

    @Column(name = "RENEWAL_COUNT")
    private Integer renewalCount;

    @Column(name = "WS_SUB_CUST_COL1", length = 4000)
    private String wsSubCustCol1;

    @Column(name = "WS_SUB_CUST_COL2", length = 4000)
    private String wsSubCustCol2;

    @Column(name = "WS_SUB_CUST_COL3", length = 4000)
    private String wsSubCustCol3;

    @Column(name = "WS_SUB_CUST_COL4", length = 4000)
    private String wsSubCustCol4;

    @Column(name = "WS_SUB_CUST_COL5", length = 4000)
    private String wsSubCustCol5;

    @Column(name = "WS_SUB_CUST_COL6", length = 4000)
    private String wsSubCustCol6;

    @Column(name = "WS_SUB_CUST_COL7", length = 4000)
    private String wsSubCustCol7;

    @Column(name = "WS_SUB_CUST_COL8", length = 4000)
    private String wsSubCustCol8;

    @Column(name = "WS_SUB_CUST_COL9", length = 4000)
    private String wsSubCustCol9;

    @Column(name = "WS_SUB_CUST_COL10", length = 4000)
    private String wsSubCustCol10;

    @Column(name = "WS_SUB_CUST_COL11", length = 4000)
    private String wsSubCustCol11;

    @Column(name = "WS_SUB_CUST_COL12", length = 4000)
    private String wsSubCustCol12;

    @Column(name = "WS_SUB_CUST_COL13", length = 4000)
    private String wsSubCustCol13;

    @Column(name = "WS_SUB_CUST_COL14", length = 4000)
    private String wsSubCustCol14;

    @Column(name = "WS_SUB_CUST_COL15", length = 4000)
    private String wsSubCustCol15;

    @Column(name = "CREATE_BY", length = 50)
    private String createBy;

    @Column(name = "EDIT_BY", length = 50)
    private String editBy;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "EDIT_DATE")
    private Date editDate;

    @Column(name = "AUDIT_ID", length = 100)
    private String auditId;

	public String getOrgnId() {
		return orgnId;
	}

	public void setOrgnId(String orgnId) {
		this.orgnId = orgnId;
	}

	public String getWsId() {
		return wsId;
	}

	public void setWsId(String wsId) {
		this.wsId = wsId;
	}

	public Integer getNoOfHitsPerDay() {
		return noOfHitsPerDay;
	}

	public void setNoOfHitsPerDay(Integer noOfHitsPerDay) {
		this.noOfHitsPerDay = noOfHitsPerDay;
	}

	public String getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(String subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public Date getSubscriptionExpiryDate() {
		return subscriptionExpiryDate;
	}

	public void setSubscriptionExpiryDate(Date subscriptionExpiryDate) {
		this.subscriptionExpiryDate = subscriptionExpiryDate;
	}

	public String getSingleMultipleFlag() {
		return singleMultipleFlag;
	}

	public void setSingleMultipleFlag(String singleMultipleFlag) {
		this.singleMultipleFlag = singleMultipleFlag;
	}

	public Integer getMaxLimit() {
		return maxLimit;
	}

	public void setMaxLimit(Integer maxLimit) {
		this.maxLimit = maxLimit;
	}

	public Date getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(Date renewalDate) {
		this.renewalDate = renewalDate;
	}

	public Integer getRenewalCount() {
		return renewalCount;
	}

	public void setRenewalCount(Integer renewalCount) {
		this.renewalCount = renewalCount;
	}

	public String getWsSubCustCol1() {
		return wsSubCustCol1;
	}

	public void setWsSubCustCol1(String wsSubCustCol1) {
		this.wsSubCustCol1 = wsSubCustCol1;
	}

	public String getWsSubCustCol2() {
		return wsSubCustCol2;
	}

	public void setWsSubCustCol2(String wsSubCustCol2) {
		this.wsSubCustCol2 = wsSubCustCol2;
	}

	public String getWsSubCustCol3() {
		return wsSubCustCol3;
	}

	public void setWsSubCustCol3(String wsSubCustCol3) {
		this.wsSubCustCol3 = wsSubCustCol3;
	}

	public String getWsSubCustCol4() {
		return wsSubCustCol4;
	}

	public void setWsSubCustCol4(String wsSubCustCol4) {
		this.wsSubCustCol4 = wsSubCustCol4;
	}

	public String getWsSubCustCol5() {
		return wsSubCustCol5;
	}

	public void setWsSubCustCol5(String wsSubCustCol5) {
		this.wsSubCustCol5 = wsSubCustCol5;
	}

	public String getWsSubCustCol6() {
		return wsSubCustCol6;
	}

	public void setWsSubCustCol6(String wsSubCustCol6) {
		this.wsSubCustCol6 = wsSubCustCol6;
	}

	public String getWsSubCustCol7() {
		return wsSubCustCol7;
	}

	public void setWsSubCustCol7(String wsSubCustCol7) {
		this.wsSubCustCol7 = wsSubCustCol7;
	}

	public String getWsSubCustCol8() {
		return wsSubCustCol8;
	}

	public void setWsSubCustCol8(String wsSubCustCol8) {
		this.wsSubCustCol8 = wsSubCustCol8;
	}

	public String getWsSubCustCol9() {
		return wsSubCustCol9;
	}

	public void setWsSubCustCol9(String wsSubCustCol9) {
		this.wsSubCustCol9 = wsSubCustCol9;
	}

	public String getWsSubCustCol10() {
		return wsSubCustCol10;
	}

	public void setWsSubCustCol10(String wsSubCustCol10) {
		this.wsSubCustCol10 = wsSubCustCol10;
	}

	public String getWsSubCustCol11() {
		return wsSubCustCol11;
	}

	public void setWsSubCustCol11(String wsSubCustCol11) {
		this.wsSubCustCol11 = wsSubCustCol11;
	}

	public String getWsSubCustCol12() {
		return wsSubCustCol12;
	}

	public void setWsSubCustCol12(String wsSubCustCol12) {
		this.wsSubCustCol12 = wsSubCustCol12;
	}

	public String getWsSubCustCol13() {
		return wsSubCustCol13;
	}

	public void setWsSubCustCol13(String wsSubCustCol13) {
		this.wsSubCustCol13 = wsSubCustCol13;
	}

	public String getWsSubCustCol14() {
		return wsSubCustCol14;
	}

	public void setWsSubCustCol14(String wsSubCustCol14) {
		this.wsSubCustCol14 = wsSubCustCol14;
	}

	public String getWsSubCustCol15() {
		return wsSubCustCol15;
	}

	public void setWsSubCustCol15(String wsSubCustCol15) {
		this.wsSubCustCol15 = wsSubCustCol15;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getEditBy() {
		return editBy;
	}

	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
    
    
}
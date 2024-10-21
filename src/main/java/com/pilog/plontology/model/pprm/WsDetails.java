package com.pilog.plontology.model.pprm;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WS_DETAILS")
@Data
public class WsDetails {
    @Id
    @Column(name = "WS_ID")
    private String wsId;
// Temp : not in DB table
    @Column(name = "WS_NAME")
    private String wsName;

    @Column(name = "WS_DESCRIPTION")
    private String wsDescription;

    @Column(name = "URL")
    private String url;

    @Column(name = "AUDIT_ID")
    private String auditId;

	public String getWsId() {
		return wsId;
	}

	public void setWsId(String wsId) {
		this.wsId = wsId;
	}

	public String getWsName() {
		return wsName;
	}

	public void setWsName(String wsName) {
		this.wsName = wsName;
	}

	public String getWsDescription() {
		return wsDescription;
	}

	public void setWsDescription(String wsDescription) {
		this.wsDescription = wsDescription;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
    
    

}

package com.pilog.plontology.model.pprm;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "WS_HISTORY")
@Data
public class WsHistory {

    @Id
    @Column(name = "ORGN_ID")
    private String orgnId; // Assuming RAW is byte[]

    @Column(name = "WS_ID")
    private String wsId;

    @Column(name = "NO_OF_HITS_PER_DAY")
    private Integer noOfHitsPerDay;

    @Column(name = "ACCESS_DATE")
    private Date accessDate;

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

	public Date getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}
    
}

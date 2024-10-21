package com.pilog.plontology.payloads;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "REPOSITORY_SEARCH_REQUEST"
)
public class RepositorySearchRequest {
    public String pageSize;
    public String pagenum;
    public String recordstartindex;
    public String recordendindex;
    public String searchData;
    public String projectionFields;
    public String filterData;
    public String orgn_id;
    public String locale;
	public String domain;
	public String erp;

	public String getErp() {
		return erp;
	}

	public void setErp(String erp) {
		this.erp = erp;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}


	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getPagenum() {
		return pagenum;
	}
	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}
	public String getRecordstartindex() {
		return recordstartindex;
	}
	public void setRecordstartindex(String recordstartindex) {
		this.recordstartindex = recordstartindex;
	}
	public String getRecordendindex() {
		return recordendindex;
	}
	public void setRecordendindex(String recordendindex) {
		this.recordendindex = recordendindex;
	}
	public String getSearchData() {
		return searchData;
	}
	public void setSearchData(String searchData) {
		this.searchData = searchData;
	}
	public String getProjectionFields() {
		return projectionFields;
	}
	public void setProjectionFields(String projectionFields) {
		this.projectionFields = projectionFields;
	}
	public String getFilterData() {
		return filterData;
	}
	public void setFilterData(String filterData) {
		this.filterData = filterData;
	}
	public String getOrgn_id() {
		return orgn_id;
	}
	public void setOrgn_id(String orgn_id) {
		this.orgn_id = orgn_id;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
    
    
}

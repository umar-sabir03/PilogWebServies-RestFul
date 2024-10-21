package com.pilog.plontology.payloads;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "SRS_REQUEST"
)
@Data
public class V10SRSRequestBean {
    @XmlElement(
            name = "ORGN_ID"
    )
    private String srsorgn;
    @XmlElement(
            name = "V10_USER_NAME"
    )
    private String v10user;
    @XmlElement(
            name = "PAGE_SIZE"
    )
    public String pageSize;
    @XmlElement(
            name = "PAGE_NUM"
    )
    public String pagenum;
    @XmlElement(
            name = "RECORD_START_INDEX"
    )
    public String recordstartindex;
    @XmlElement(
            name = "RECORD_END_INDEX"
    )
    public String recordendindex;
    @XmlElement(
            name = "SEARCH_DATA"
    )
    public String searchData;
    @XmlElement(
            name = "PROJECTION_FIELDS"
    )
    public String projectionFields;
    @XmlElement(
            name = "SORT_ORDER"
    )
    public String sortOrder;
    @XmlElement(
            name = "SORTED_DATA_FIELD"
    )
    public String sortedDataField;
    @XmlElementWrapper(
            name = "SRS_GRID_FILTER"
    )
    @XmlElement(
            name = "items"
    )
    public List<SRSGridFilterDTO> filterData;

}

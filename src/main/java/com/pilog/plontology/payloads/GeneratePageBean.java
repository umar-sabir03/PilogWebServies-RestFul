package com.pilog.plontology.payloads;

import lombok.Data;

import java.util.List;
@Data

public class GeneratePageBean {

    private String pageSize;
    private String pageNum;
    private String recordStartIndex;
    private String recordEndIndex;
    private String projectionFields;
    private String sortOrder;
    private String sortedDataField;

    private List<GenerateFilterDTO> filterData;
}
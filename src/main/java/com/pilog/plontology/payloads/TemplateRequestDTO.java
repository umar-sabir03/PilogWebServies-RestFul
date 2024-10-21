package com.pilog.plontology.payloads;

import lombok.Data;

@Data
public class TemplateRequestDTO {
    public String recordstartIndex;
    public String recordendIndex;
    public String orgnId;
    public String term;
    public String languageId;
    public String domain;
    public String unspscCode;
    public String operator;
    public String conceptId;
    public String sortOrder;
    public String sortField;
    public String pageNum;
    public String pageSize;
    public String filterCount;
    public String filterQuery;
    public String multilingualFlag;
}

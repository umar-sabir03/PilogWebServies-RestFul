package com.pilog.plontology.payloads;

import lombok.Data;

@Data
public class TemplateSearchResponse{
    public Long count;
    public String drId;
    public String conceptId;
    public String termId;
    public String definitionId;
    public String abbreviationId;
    public String definition;
    public String blobContent;
    public String term;
    public String abbreviation;
    public String unspscCode;
    public String unspscDescription;
    public String category;
    public String subcategory;
    public String catCode;
    public String subCatCode;
    public String industry;
    public String discipline;
    public String disciplineAbbr;
    public String hlCode;
}

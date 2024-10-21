package com.pilog.plontology.payloads;

import lombok.Data;

import java.util.List;

@Data
public class TemplateResInsertDTO {
    private List<TemplateInsertResponse> otherLangResponseList;
    private List<TemplateInsertResponse> repResponseList;

}

package com.pilog.plontology.payloads;

import lombok.Data;

import java.util.List;

@Data
public class TemplateInfoResponse
{
    public List<TemplateInfoData> templateInfoDataList;
}

package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.TemplateInsertResponse;
import com.pilog.plontology.payloads.TemplateRequestDTO;
import com.pilog.plontology.payloads.TemplateResInsertDTO;
import com.pilog.plontology.repository.apex.TemplateInsertRepository;
import com.pilog.plontology.service.ITemplateInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TempateInsertServiceImpl implements ITemplateInsertService {
    @Autowired
    private TemplateInsertRepository templateInsertRepository;
    @Override
    public TemplateResInsertDTO classTemplateInsert(TemplateRequestDTO request) {

        TemplateResInsertDTO templateResponseDto = new TemplateResInsertDTO();
        List<TemplateInsertResponse> responseList = new ArrayList<>();
        try {
            request.languageId = request.languageId == null ? "" : request.languageId;
            request.languageId = request.languageId.equalsIgnoreCase("?") ? "" : request.languageId;
            request.languageId = request.languageId.equalsIgnoreCase("") ? "1007-1#LG-000001#1" : request.languageId;

            request.orgnId = request.orgnId == null ? "" : request.orgnId;
            request.orgnId = request.orgnId.equalsIgnoreCase("?") ? "" : request.orgnId;
            request.orgnId = request.orgnId.equalsIgnoreCase("") ? "E6EE49F8383C494098B18D06C64DDFF0" : request.orgnId;

            System.out.println("request.languageId:::::"+request.languageId);
            List<TemplateInsertResponse> templateData = templateInsertRepository.getTemplateData(request.orgnId, "1007-1#LG-000001#1",request.domain, request.conceptId);
            templateResponseDto.setRepResponseList(responseList);
            if (request.languageId != null && !"1007-1#LG-000001#1".equalsIgnoreCase(request.languageId)) {
                List<TemplateInsertResponse> responseOtherList = templateInsertRepository.getTemplateData(request.orgnId, request.languageId,request.domain, request.conceptId);
                templateResponseDto.setOtherLangResponseList(responseOtherList);
            }
        } catch (Exception e) {
        }
        return templateResponseDto;
    }
}

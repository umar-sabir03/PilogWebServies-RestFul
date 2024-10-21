package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.Template;
import com.pilog.plontology.payloads.TemplateRequest;
import com.pilog.plontology.payloads.TemplateResponse;
import com.pilog.plontology.repository.apex.TemplateRepository;
import com.pilog.plontology.service.IWelspunSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WelspunSearchImpl implements IWelspunSearch {

    @Autowired
    private TemplateRepository templateRepository;

    @Override
    public TemplateResponse fetchTemplate(TemplateRequest request) {
        TemplateResponse response = new TemplateResponse();

        if (request.getConceptId() == null || request.getConceptId().isEmpty()) {
            request.setConceptId("");
        }
        if (request.getClassterm() == null || request.getClassterm().isEmpty()) {
            request.setClassterm("");
        }

        request.setOrgnId("353B1EBE30A137A0E053270110AC5729");
        if (!request.getClassterm().toUpperCase().contains("TOWEL") && !templateRepository.isTowel(request.getClassterm())) {
            request.setOrgnId("E6EE49F8383C494098B18D06C64DDFF0");
        }

        List<Object[]> results = templateRepository.fetchTemplates(request.getOrgnId(), request.getClassterm());

        for (Object[] row : results) {
            Template property = new Template();
            property.setItemRef(getString(row[0]));
            property.setWord(getString(row[1]));
            property.setODef(getString(row[2]));
            property.setMandatoryInd(getString(row[3]));
            property.setOSeq(getString(row[4]));
            property.setOStxtSeq(getString(row[5]));
            property.setOAbbr(getString(row[6]));
            property.setDatatypeRef(getString(row[7]));
            property.setConceptId(getString(row[8]));
            property.setHighLvlInd(getString(row[9]));

            response.getPropertiesList().add(property);
        }

        return response;
    }

    private String getString(Object obj) {
        return obj != null ? obj.toString() : "";
    }
}

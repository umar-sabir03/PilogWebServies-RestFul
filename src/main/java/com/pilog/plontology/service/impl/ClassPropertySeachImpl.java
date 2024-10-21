package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.PropRequestDTO;
import com.pilog.plontology.payloads.PropResponse;
import com.pilog.plontology.payloads.PropertyResponseDTO;
import com.pilog.plontology.repository.apex.PropertyRepository;
import com.pilog.plontology.service.IClassPropertySearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassPropertySeachImpl implements IClassPropertySearch {
    @Autowired
    private PropertyRepository propertyRepository;
    @Override
    public PropertyResponseDTO fetchPropValues(PropRequestDTO request) {
        PropertyResponseDTO propertyResponseDto = new PropertyResponseDTO();
        List<PropResponse> responseList = new ArrayList<>();
        List<PropResponse> otherLangResponseList = new ArrayList<>();
        setDefaultsForRequest(request);

        responseList = propertyRepository.fetchProperties(request.getOrgnId(), request.getClassConceptId(), "1007-1#LG-000001#1");
        propertyResponseDto.setRepResponseList(responseList);

        if (request.getLanguageId() != null && !"1007-1#LG-000001#1".equalsIgnoreCase(request.getLanguageId())) {
            System.out.println("Fetching response for other language");
            otherLangResponseList = propertyRepository.fetchProperties(request.getOrgnId(), request.getClassConceptId(), request.getLanguageId());
            propertyResponseDto.setOtherLangResponseList(otherLangResponseList);
        }

        return propertyResponseDto;
    }

    private void setDefaultsForRequest(PropRequestDTO request) {
        if (request.getOrgnId() == null || request.getOrgnId().isEmpty()) {
            request.setOrgnId("E6EE49F8383C494098B18D06C64DDFF0");
        }
        if (request.getClassConceptId() == null || request.getClassConceptId().isEmpty() || "?".equals(request.getClassConceptId())) {
            request.setClassConceptId("");
        }
        if (request.getLanguageId() == null || request.getLanguageId().isEmpty() || "?".equals(request.getLanguageId())) {
            request.setLanguageId("1007-1#LG-000001#1");
        }
    }
}

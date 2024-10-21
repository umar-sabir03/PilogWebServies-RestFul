package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.Property;
import com.pilog.plontology.payloads.PropertyRequest;
import com.pilog.plontology.payloads.PropertyResponse;
import com.pilog.plontology.repository.apex.PropertyRepository;
import com.pilog.plontology.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyServiceImpl implements IPropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Override
    public PropertyResponse listProperties(PropertyRequest request) {
        List<Property> propertyList = new ArrayList<>();
        List<Object[]> results = propertyRepository.getPropertiesByConceptId(request.getConceptId());

        for (Object[] row : results) {
            Property property = new Property();
            property.setItemRef((String) row[0]);
            property.setWord((String) row[1]);
            property.setDefinition((String) row[2]);
            property.setMandatoryIndicator((String) row[3]);
            property.setSequence((String) row[4]);
            property.setShortTextSequence((String) row[5]);
            property.setAbbreviation((String) row[6]);
            property.setDataTypeReference((String) row[7]);
            property.setConceptId((String) row[8]);
            propertyList.add(property);
        }
        PropertyResponse response = new PropertyResponse();
        response.setPropertiesList(propertyList);
        return response;
    }
}

package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.*;
import com.pilog.plontology.repository.apex.CharacteristicsRepository;
import com.pilog.plontology.service.ISearchCharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchCharacteristicsServiceImpl implements ISearchCharacteristicsService {

    @Autowired
    private CharacteristicsRepository characteristicsRepository;

    @Override
    public CharacteristicsResponse list(CharacteristicsRequest request) {
        CharacteristicsResponse response = new CharacteristicsResponse();
        List<Characteristics> characteristicsList = new ArrayList<>();

        if (request.getClazz() == null || request.getClazz().isEmpty()) {
            request.setClazz("*");
        }

        List<SimpleCharacteristicDto> characteristicDtoList = characteristicsRepository.findByClass(request.getClazz());

        for (SimpleCharacteristicDto characteristicDto : characteristicDtoList) {
            Characteristics characteristics = new Characteristics();
            characteristics.setOntology(Ontology.builder()
                    .clazz(characteristicDto.getClazz())
                    .conceptId(characteristicDto.getConceptId()).build());
            List<String> properties = characteristicsRepository.findPropertiesByClass(characteristicDto.getClazz());
            Properties props = new Properties();
            props.setProperty(properties);
            characteristics.setProperties(props);

            characteristicsList.add(characteristics);
        }
        response.setCharacteristicsList(characteristicsList);
        return response;
    }
}

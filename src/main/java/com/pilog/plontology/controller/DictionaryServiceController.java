package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.*;
import com.pilog.plontology.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PIOntology/DictionaryService")
public class DictionaryServiceController {
    @Autowired
    private ISearchCharacteristicsService searchService;
    @Autowired
    private ISearchImageService searchImageSerice;
    @Autowired
    private ISearchOntology searchOntology;
    @Autowired
    private IPropertyService propertyService;

    @Autowired
    private ITemplateInfoService templateInfoService;


    @PostMapping("/searchCharacteristics")
    public ResponseEntity<CharacteristicsResponse> searchCharacteristics(@RequestBody CharacteristicsRequest characteristicsRequest) {
        CharacteristicsResponse response = new CharacteristicsResponse();
        try {
            response = searchService.list(characteristicsRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CharacteristicsResponse(/* Error details here */));
        }
    }

    @PostMapping("/searchImage")
    public ResponseEntity<ImageResponse> searchImage(@RequestBody ImageRequest request) {
        ImageResponse response = new ImageResponse();
        try {
            response = searchImageSerice.show(request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/searchOntology")
    public ResponseEntity<OntologyResponse> searchOntology(@RequestBody OntologyRequest request) {
        OntologyResponse response = new OntologyResponse();
        try {
            response = searchOntology.list(request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/SearchProperty")
    public ResponseEntity<PropertyResponse> listProperties(@RequestBody PropertyRequest request) {
        PropertyResponse response = propertyService.listProperties(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/fetch")
    public ResponseEntity<TemplateInfoResponse> fetch(@RequestBody MLDataRequestDto mlDataRequestDto) {
        TemplateInfoResponse response = templateInfoService.fetch(
                mlDataRequestDto.getTerm(),
                mlDataRequestDto.getOrgId(),
                mlDataRequestDto.getIsNested()
        );
        return ResponseEntity.ok(response);
    }


}

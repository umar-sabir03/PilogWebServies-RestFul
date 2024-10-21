package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.PropRequestDTO;
import com.pilog.plontology.payloads.PropertyResponseDTO;
import com.pilog.plontology.service.IClassPropertySearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PIOntology/DictionaryPropService")
public class DictionaryPropServiceController {


    @Autowired
    private IClassPropertySearch propertySearch;

    @PostMapping("/fetchPropValues")
    public ResponseEntity<PropertyResponseDTO> fetchPropValues(@RequestBody PropRequestDTO request) {
        if (request.getOrgnId() == null || request.getOrgnId().isEmpty()) {
            request.setOrgnId("E6EE49F8383C494098B18D06C64DDFF0");
        }

        PropertyResponseDTO propertyResponseDTO = new PropertyResponseDTO();

        try {
            propertyResponseDTO = propertySearch.fetchPropValues(request);
            return new ResponseEntity<>(propertyResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

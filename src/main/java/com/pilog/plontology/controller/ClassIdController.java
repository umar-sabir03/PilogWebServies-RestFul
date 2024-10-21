package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.GenerateClassTermDTO;
import com.pilog.plontology.service.IClassIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/GenerateConcept/ConceptIdCreation")
public class ClassIdController {
    @Autowired
    private IClassIdService classIdService;
    @PostMapping("/ConceptIdCreation")
    public ResponseEntity<String> generateTerm(
            @RequestBody GenerateClassTermDTO generateClassTermDTO){

        String response = classIdService.generateTerm(generateClassTermDTO);
        return ResponseEntity.ok(response);
    }


}

package com.pilog.plontology.controller;


import com.pilog.plontology.payloads.GenerateTermDTO;
import com.pilog.plontology.payloads.GenerateTermDrDTO;
import com.pilog.plontology.payloads.TaxonomyOperationRequestDto;
import com.pilog.plontology.service.IGenerateTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ConceptId")
public class TaxonomyOperationsController {
    @Autowired
    private IGenerateTermService generateTermService;
    @PostMapping("/ConceptIdCreation")
    public ResponseEntity<String> ConceptIdClasses(@Valid @RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        String message = generateTermService.generateConceptId(taxonomyOperationRequestDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PostMapping("/ConceptIdReg")
    public ResponseEntity<List<GenerateTermDTO>> statusReg(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDTO> responseData = new ArrayList<>();
        responseData = generateTermService.generateConceptReg(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/ConceptIdApp")
    public ResponseEntity<List<GenerateTermDTO>> statusApp(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDTO> responseData = new ArrayList<>();
         responseData = generateTermService.generateConceptApp(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/ConceptIdRegProperty")
    public ResponseEntity<List<GenerateTermDTO>> statusPropertyReg(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDTO> responseData = new ArrayList<>();
        responseData = generateTermService.generateConceptPropertyReg(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/ConceptIdAppProperty")
    public ResponseEntity<List<GenerateTermDTO>> statusPropertyApp(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDTO> responseData = new ArrayList<>();
        responseData= generateTermService.generateConceptPropertyApp(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/ConceptIdDr")
    public ResponseEntity<List<GenerateTermDTO>>  statusDr(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDTO> responseData = new ArrayList<>();
        responseData= generateTermService.generateConceptDr(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/ConceptIdDelete")
    public ResponseEntity<List<GenerateTermDTO>> statusDelete(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDTO> responseData = new ArrayList<>();
        responseData =generateTermService.ConceptDelete(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/ConceptIdPropDelete")
    public ResponseEntity<List<GenerateTermDTO>> statusPropDelete(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDTO> responseData = new ArrayList<>();
        responseData = generateTermService.ConceptPropDelete(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/ConceptIdPropModify")
    public ResponseEntity<List<GenerateTermDTO>> statusPropModify(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDTO> responseData = new ArrayList<>();
        responseData = generateTermService.ConceptPropModify(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/ConceptTxmnyProcess")
    public ResponseEntity<List<Object[]>> statusTxmnyProcess(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<Object[]> responseData = new ArrayList<>();
        responseData= generateTermService.generateConceptTxmnyProcess(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/ConceptIdClass")
    public ResponseEntity<List<GenerateTermDTO>> conceptIdClassAndProperty(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDTO> responseData = new ArrayList<>();
        responseData = generateTermService.generateConceptClass(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/ConceptIdDrid")
    public ResponseEntity<List<String>> getDrid(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<String> responseData = new ArrayList<>();
        responseData = generateTermService.getDrid(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/ConceptIdIrdi")
    public ResponseEntity<List<String>> getIrdi(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<String> responseData = new ArrayList<>();
        responseData = generateTermService.getIrdi(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/ConceptIdDridInsertion")
    public ResponseEntity<List<GenerateTermDrDTO>> getNewDridInsertion(@RequestBody List<GenerateTermDrDTO> resultList) {
        List<GenerateTermDrDTO> responseData = new ArrayList<>();
        responseData = generateTermService.getNewDridInsertion(resultList);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    } @PostMapping("/ConceptIdDrApp")
    public ResponseEntity<List<GenerateTermDrDTO>> getDridResults(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDrDTO> responseData = new ArrayList<>();
        responseData = generateTermService.generateConceptDrClassApp(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/ConceptIdProtyApp")
    public ResponseEntity<List<GenerateTermDrDTO>> getDridProtyResults(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDrDTO> responseData = new ArrayList<>();
        responseData = generateTermService.generateConceptDrProtyApp(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    } @PostMapping("/ConceptIdDtdrUpdate")
    public ResponseEntity<GenerateTermDrDTO> dtDrUpdate(@RequestBody TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
       GenerateTermDrDTO responseData = new GenerateTermDrDTO();
        responseData = generateTermService.getDtDrChangesUpdate(taxonomyOperationRequestDto);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }



}

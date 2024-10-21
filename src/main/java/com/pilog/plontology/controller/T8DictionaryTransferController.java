package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.DataRequirementDataRow;
import com.pilog.plontology.payloads.DictionaryRequestDTO;
import com.pilog.plontology.payloads.PreferenceOntologyDataRow;
import com.pilog.plontology.service.IT8DictionaryTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/T8DictionaryTransferServiceGENERICQA/T8DictionaryTransferService")
public class T8DictionaryTransferController {
    @Autowired
    private IT8DictionaryTransferService dictionaryTransferService;

    @PostMapping("/getPreferenceOntology")
    public ResponseEntity<List<PreferenceOntologyDataRow>> getPreferenceOntology(@RequestBody DictionaryRequestDTO dictionaryRequestDTO) {
        List<PreferenceOntologyDataRow> response = dictionaryTransferService.getPreferenceOntology(dictionaryRequestDTO.getOrgID(), dictionaryRequestDTO.getInstance(), dictionaryRequestDTO.getSsUsername());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getPreferenceOntologyWithLanguageID")
    public ResponseEntity<List<PreferenceOntologyDataRow>> getPreferenceOntologyWithLanguageID(@RequestBody DictionaryRequestDTO dictionaryRequestDTO)  {
        List<PreferenceOntologyDataRow> response = dictionaryTransferService.getPreferenceOntologyWithLanguageID(dictionaryRequestDTO.getOrgID(), dictionaryRequestDTO.getLanguageID(), dictionaryRequestDTO.getInstance(), dictionaryRequestDTO.getSsUsername());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getDataRequirementWithLanguageID")
    public ResponseEntity<List<DataRequirementDataRow>> getDataRequirementWithLanguageID(@RequestBody DictionaryRequestDTO dictionaryRequestDTO) {
        List<DataRequirementDataRow> response = dictionaryTransferService.getDataRequirementWithLanguageID(dictionaryRequestDTO.getOrgID(), dictionaryRequestDTO.getLanguageID(), dictionaryRequestDTO.getInstance(), dictionaryRequestDTO.getSsUsername());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getDataRequirementWithLanguageIDCount")
    public ResponseEntity<String> getDataRequirementWithLanguageIDCount(@RequestBody DictionaryRequestDTO dictionaryRequestDTO) {
        String response = dictionaryTransferService.getDataRequirementWithLanguageIDCount(dictionaryRequestDTO.getOrgID(), dictionaryRequestDTO.getLanguageID(), dictionaryRequestDTO.getInstance(), dictionaryRequestDTO.getSsUsername());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getUoMWithLanguageIDCount")
    public ResponseEntity<String> getUoMWithLanguageIDCount(@RequestBody DictionaryRequestDTO dictionaryRequestDTO)  {
        String response =  dictionaryTransferService.getUoMWithLanguageIDCount(dictionaryRequestDTO.getOrgID(), dictionaryRequestDTO.getLanguageID(), dictionaryRequestDTO.getInstance(), dictionaryRequestDTO.getSsUsername());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getTranslationsCount")
    public ResponseEntity<String> getTranslationsCount(@RequestBody DictionaryRequestDTO dictionaryRequestDTO) {
        String response =  dictionaryTransferService.getTranslationsCount(dictionaryRequestDTO.getOrgID(), dictionaryRequestDTO.getLanguageID(), dictionaryRequestDTO.getInstance(), dictionaryRequestDTO.getSsUsername());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getDataRequirementWithLanguageIDCountforInsert")
    public ResponseEntity<String> getDataRequirementWithLanguageIDCountforInsert(@RequestBody DictionaryRequestDTO dictionaryRequestDTO)  {
        String response =  dictionaryTransferService.getDataRequirementWithLanguageIDCountforInsert(dictionaryRequestDTO.getOrgID(), dictionaryRequestDTO.getLanguageID(), dictionaryRequestDTO.getInstance(), dictionaryRequestDTO.getSsUsername());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getDataRequirementWithLanguageIDCountforDelete")
    public ResponseEntity<String> getDataRequirementWithLanguageIDCountforDelete(@RequestBody DictionaryRequestDTO dictionaryRequestDTO)  {
        String response = dictionaryTransferService.getDataRequirementWithLanguageIDCountforDelete(dictionaryRequestDTO.getOrgID(), dictionaryRequestDTO.getLanguageID(), dictionaryRequestDTO.getInstance(), dictionaryRequestDTO.getSsUsername());
        return ResponseEntity.ok().body(response);
    }
}

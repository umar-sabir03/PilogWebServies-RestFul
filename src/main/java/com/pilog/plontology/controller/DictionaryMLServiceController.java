package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.MLDataRequestDto;
import com.pilog.plontology.payloads.TemplateInfoResponse;
import com.pilog.plontology.service.ITemplateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PIOntology/DictionaryMLService")
public class DictionaryMLServiceController {
    @Autowired
   private ITemplateInfoService templateInfoService;
    @PostMapping("/fetchMLData")
    public ResponseEntity<TemplateInfoResponse> fetchMLData(
            @RequestBody MLDataRequestDto mlDataRequestDto){

        TemplateInfoResponse response = templateInfoService.fetchMLData(mlDataRequestDto.getTerm(), mlDataRequestDto.getOrgId(), mlDataRequestDto.getIsNested(), mlDataRequestDto.getLanguageId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/fetchMLPropswithClassTerm")
    public ResponseEntity<TemplateInfoResponse> fetchMLPropswithClassTerm(
            @RequestBody MLDataRequestDto mlDataRequestDto) {

        TemplateInfoResponse response = templateInfoService.fetchMLPropswithClassTerm(mlDataRequestDto.getTerm(), mlDataRequestDto.getOrgId(), mlDataRequestDto.getLanguageId());
        if (response != null && !response.getTemplateInfoDataList().isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}

package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.V10TranslateRequest;
import com.pilog.plontology.payloads.V10TranslateResponse;
import com.pilog.plontology.service.IGenericTranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V10Translator/V10GenericTranslator")
public class GenericTranslatorController {
    @Autowired
    private IGenericTranslatorService genericTranslatorService;


    @PostMapping("/GenericTranslatorService")
    public ResponseEntity<V10TranslateResponse> getGenericTranslator(@RequestBody V10TranslateRequest translateRequest){

        V10TranslateResponse genericTranslator = genericTranslatorService.getGenericTranslator(translateRequest);
        return new ResponseEntity<>(genericTranslator, HttpStatus.OK);
    }
}

package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.TranslationRequest;
import com.pilog.plontology.service.ILocaleProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/WSRep/translate")
public class TranslateController {
    @Autowired
    private ILocaleProcessService localeProcess;

    @PostMapping("/doProcess")
    public ResponseEntity<String> doProcess(@RequestBody TranslationRequest request)
    {
        if (request.getOrgnId() == null) {
            request.setOrgnId("E6EE49F8383C494098B18D06C64DDFF0");
        }
        String response = localeProcess.doTranslate(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

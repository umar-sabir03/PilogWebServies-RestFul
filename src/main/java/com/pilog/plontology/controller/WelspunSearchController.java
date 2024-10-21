package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.TemplateRequest;
import com.pilog.plontology.payloads.TemplateResponse;
import com.pilog.plontology.service.IWelspunSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/WelspunService")
public class WelspunSearchController {

    @Autowired
    private IWelspunSearch welspunSearch;

    @CrossOrigin(origins = "*")
    @PostMapping("/fetchTemplate")
    public ResponseEntity<TemplateResponse> fetchTemplate(@RequestBody TemplateRequest request) {
        TemplateResponse templateResponse = new TemplateResponse();
        try {
            templateResponse = welspunSearch.fetchTemplate(request);
            return ResponseEntity.ok(templateResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new TemplateResponse());
        }
    }
}

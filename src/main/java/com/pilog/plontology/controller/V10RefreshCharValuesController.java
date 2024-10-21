package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.RefreshCharValuesRequest;
import com.pilog.plontology.service.IRefreshCharSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V10PilogWSRep/V10RefreshCharValues")
public class V10RefreshCharValuesController {

    @Autowired
    private IRefreshCharSearch refreshCharSearch;

    @PostMapping("/refreshCharValues")
    public void refreshCharValues(@RequestBody RefreshCharValuesRequest charValuesRequest) {
        try {
               refreshCharSearch.refreshCharValues(charValuesRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

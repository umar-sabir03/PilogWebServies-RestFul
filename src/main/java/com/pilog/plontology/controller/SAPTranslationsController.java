package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.OTerm;
import com.pilog.plontology.payloads.RequestDto;
import com.pilog.plontology.service.ISAPTranslationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/SAPTranslations/service")
public class SAPTranslationsController {
    @Autowired
    private ISAPTranslationsService isapTranslationsService;
    public ResponseEntity<List<OTerm>> search(@RequestBody RequestDto request) {
        List<OTerm> response = new ArrayList<>();
        try {
   //         DataSource ds = (DataSource)ctx.lookup("jdbc/PIPLCLOUD_MDQM8_2QA");
             response = isapTranslationsService.search(request);

        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, (String)null, ex);
        }
        return ResponseEntity.ok().body(response);
    }
}


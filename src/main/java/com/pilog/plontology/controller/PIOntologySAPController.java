package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.*;
import com.pilog.plontology.service.IPIOntologySAPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PIOntologySAP/PPO_Interface")
public class PIOntologySAPController {
    @Autowired
    private IPIOntologySAPService piOntologySAPService;
    @PostMapping("/detail")
    public ResponseEntity<List<MTRLPPOTermDetail>> detail(@RequestBody ClassRequestDetailInfo request)
    {
        List<MTRLPPOTermDetail> response = piOntologySAPService.getClassDetailData(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/simple")
    public ResponseEntity<List<MTRLPPOTermSimple>> simple(@RequestBody ClassRequestDetailInfo request)
    {
        List<MTRLPPOTermSimple> response = piOntologySAPService.getClassSimpleData(request);

         return ResponseEntity.ok().body(response);
    }

    @PostMapping("/advanced")
    public ResponseEntity<List<MTRLPPOTERMAdvanced>> advanced( @RequestBody ClassRequesAdvancedInfo request)
    {
        List<MTRLPPOTERMAdvanced> response = piOntologySAPService.getClassAdvanceData(request);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/sapdetailinfo")
    public ResponseEntity<List<MTRLPPOTermDetail>> sapdetailinfo( @RequestBody ClassRequestSAPDetailInfo request)
    {
        List<MTRLPPOTermDetail> response = piOntologySAPService.getClassSAPData(request);

        return ResponseEntity.ok().body(response);
    }
}

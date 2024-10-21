package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.ReferenceRequest;
import com.pilog.plontology.payloads.ReferenceResponse;
import com.pilog.plontology.service.IReferenceUtilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Dictionary/Reference")
public class ReferenceController {

    private IReferenceUtilService utility;
    @PostMapping("/searchReference")
    public ResponseEntity<ReferenceResponse> searchReference(@RequestBody ReferenceRequest request) {
        System.out.println(request.getPartNumber());
        ReferenceResponse response = utility.partNoInfo(request);
        return ResponseEntity.ok(response);
    }
}

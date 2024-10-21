package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.TemplateRequestDTO;
import com.pilog.plontology.payloads.TemplateSearchResponse;
import com.pilog.plontology.service.ITemplateSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ClassTemplate")
public class TemplateSearchController {
    @Autowired
    private ITemplateSearch templateSearch;

    @PostMapping("classTemplateSearch")
    public ResponseEntity<List<TemplateSearchResponse> > fetchClassTemplate(@RequestBody TemplateRequestDTO request) {

        List<TemplateSearchResponse> templateResponseDTO = new ArrayList<>();
        try {
            if (request.getOrgnId() == null) {
                request.setOrgnId("");
            }
            if ("".equalsIgnoreCase(request.getOrgnId())) {
                request.setOrgnId("E6EE49F8383C494098B18D06C64DDFF0");
            }
            templateResponseDTO = templateSearch.fetchClassTemplate(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(templateResponseDTO, HttpStatus.OK);
    }
}

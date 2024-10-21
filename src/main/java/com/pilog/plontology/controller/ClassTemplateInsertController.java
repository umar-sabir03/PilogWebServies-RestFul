package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.TemplateRequestDTO;
import com.pilog.plontology.payloads.TemplateResInsertDTO;
import com.pilog.plontology.service.ITemplateInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PilogClassTemplate/ClassTemplateInsert")
public class ClassTemplateInsertController {
    @Autowired
    private ITemplateInsertService templateInsert;

    @PostMapping("/classTemplateInsert")
    public ResponseEntity<TemplateResInsertDTO> fetchClassTemplate(@RequestBody TemplateRequestDTO request) {
        TemplateResInsertDTO templateResponseDTO = new TemplateResInsertDTO();
        try {

            if (request.getOrgnId() == null) {
                request.setOrgnId("");
            }
            if ("".equalsIgnoreCase(request.getOrgnId())) {
                request.setOrgnId("E6EE49F8383C494098B18D06C64DDFF0");
            }
            templateResponseDTO =    templateInsert.classTemplateInsert(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(templateResponseDTO);

    }
}

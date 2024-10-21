package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.ResponseDTO;
import com.pilog.plontology.service.IVisionCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V10AddToCatalogueService/VisionCatalogueService")
public class V10CatalogueController {

    @Autowired
    private IVisionCatalogService visionCatalogService;
    @PostMapping("/VisionMasterQuery")
    public ResponseEntity<ResponseDTO> visionMasterQuery(@RequestBody String recordNo) {
        ResponseDTO responceDTO = new ResponseDTO();
        try {

            responceDTO = visionCatalogService.VisionMasterQuery(recordNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(responceDTO,HttpStatus.OK);
    }

    @PostMapping("/VisionDocumentQuery")
    public ResponseEntity<ResponseDTO> visionDocumentQuery(@RequestBody String recordNo) {
        ResponseDTO responceDTO = new ResponseDTO();
        try {

            responceDTO = visionCatalogService.VisionDocumentQuery(recordNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(responceDTO,HttpStatus.OK);
    }

    @PostMapping("/VisionTextQuery")
    public ResponseEntity<ResponseDTO> VisionTextQuery(@RequestBody String recordNo) {
        ResponseDTO responceDTO = new ResponseDTO();
        try {

            responceDTO = visionCatalogService.VisionTextQuery(recordNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(responceDTO,HttpStatus.OK);
    }

    @PostMapping("/VisionReferenceQuery")
    public ResponseEntity<ResponseDTO> VisionReferenceQuery(@RequestBody String recordNo) {
        ResponseDTO responceDTO = new ResponseDTO();
        try {

            responceDTO = visionCatalogService.VisionReferenceQuery(recordNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(responceDTO,HttpStatus.OK);
    }

    @PostMapping("/VisionCharQuery")
    public ResponseEntity<ResponseDTO> VisionCharQuery(@RequestBody String recordNo) {
        ResponseDTO responceDTO = new ResponseDTO();
        try {

            responceDTO = visionCatalogService.VisionCharQuery(recordNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(responceDTO,HttpStatus.OK);
    }
}

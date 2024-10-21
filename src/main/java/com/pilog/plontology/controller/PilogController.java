package com.pilog.plontology.controller;

import com.pilog.plontology.exception.BadRequestException;
import com.pilog.plontology.payloads.RepositorySearchRequest;
import com.pilog.plontology.service.IPilogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/PIOntology")
public class PilogController {
    @Autowired
    private IPilogRepository pilogRepository;

    @PostMapping(value="/PiLogRepository", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> searchRepository(@RequestBody RepositorySearchRequest request){
        String s = null;
            if (request.getOrgn_id()==null || "".equals(request.getOrgn_id()))
                throw new BadRequestException(HttpStatus.BAD_REQUEST,"orgn can not be empty","orgn.is.empty");
        try {
            s = pilogRepository.searchRepository(request);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(s);
    }
    @GetMapping("/hello")
    public ResponseEntity<String> testing()   {
        String s = null;
        try {
            s = "Hello My Dear Pilogians";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(s);
    }
}

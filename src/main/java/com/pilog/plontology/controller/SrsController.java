package com.pilog.plontology.controller;

import com.pilog.plontology.payloads.*;
import com.pilog.plontology.service.IV10SRSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/V10SRSService")
public class SrsController {

    @Autowired
    private IV10SRSService v10SRSService;

    @PostMapping(value = "/srsHistory")
    public ResponseEntity<SRSDataResponse> getSRSData(@RequestBody V10SRSRequestBean requestBean) {
        SRSDataResponse dataResponse = new SRSDataResponse();
        HttpStatus status = HttpStatus.OK;

        try {
            List<SRSResponseDTO> list = new ArrayList<>();
            if (requestBean != null) {
                list = v10SRSService.getSrsData(requestBean);
            }
            dataResponse.setRepResponseList(list);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }

        return new ResponseEntity<>(dataResponse, status);
    }

    @PostMapping(value = "/saveSRSFile")
    public ResponseEntity<String> saveSRSFile(@Valid @RequestBody V10SRSBean v10SRSBean) {
        String result = "Success";
        HttpStatus status = HttpStatus.OK;
        try {
            if (v10SRSBean != null) {
                String filePath = "C:/Test/Server/Download";
                File fileDir = new File(filePath);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                List<SRSFileRequestDTO> fileListRequests = v10SRSBean.getSrsFileRequestData();
                if (fileListRequests != null && !fileListRequests.isEmpty()) {
                    for (SRSFileRequestDTO decodeFile : fileListRequests) {
                        byte[] decodedBytes = Base64.getDecoder().decode(decodeFile.getFileEncodedString());
                        File file = new File(fileDir.getAbsolutePath() + File.separator + decodeFile.getFileName());
                        try (FileOutputStream fop = new FileOutputStream(file)) {
                            fop.write(decodedBytes);
                        } catch (IOException e) {
                            status = HttpStatus.INTERNAL_SERVER_ERROR;
                            e.printStackTrace();
                        }
                    }
                }
                result = v10SRSService.createSRSForV10(v10SRSBean);
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result = "Fail";
            e.printStackTrace();
        }

        return new ResponseEntity<>(result, status);
    }

}

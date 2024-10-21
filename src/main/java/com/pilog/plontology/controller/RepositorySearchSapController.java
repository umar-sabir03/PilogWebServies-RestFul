package com.pilog.plontology.controller;

import com.pilog.plontology.access.WSAccessHistory;
import com.pilog.plontology.payloads.RepResponse;
import com.pilog.plontology.payloads.RepResponseDTO;
import com.pilog.plontology.payloads.RepositoryRequestSAPDTO;
import com.pilog.plontology.service.IRepSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/PilogRepositorySAP/RepositorySearchSAP")
public class RepositorySearchSapController {
    @Autowired
    private WSAccessHistory wsAccessHistory;
    @Autowired
    private IRepSearchService repSearchService;

    private final String WS_NAME = "RepositorySearchSAP";
    @PostMapping("repositorySearch")
    public ResponseEntity<?> searchRepository(@RequestBody RepositoryRequestSAPDTO request){
        List<RepResponse> list = new ArrayList<>();
        RepResponseDTO repResponseDTO = new RepResponseDTO();
        try {

            if (request.getOrgnId() == null) {
                request.setOrgnId("");
            }
            if ("".equalsIgnoreCase(request.getOrgnId())) {
                request.setOrgnId("E6EE49F8383C494098B18D06C64DDFF0");
            }
            String validatorMessage = wsAccessHistory.validateSubscription(request.getOrgnId(), WS_NAME);
            System.out.println("validator msg::::" + validatorMessage);
            if (!validatorMessage.equals("")) {
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("responseData", new ArrayList<>());
                responseMap.put("message", validatorMessage);
                responseMap.put("wsstatus", "failure");

                return ResponseEntity.ok().body(responseMap);
            } else {
                System.out.println("else of xml");
                repResponseDTO = repSearchService.searchRepositoryXml(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(repResponseDTO);
    }
}

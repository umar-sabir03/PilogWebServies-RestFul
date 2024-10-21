package com.pilog.plontology.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pilog.plontology.access.WSAccessHistory;
import com.pilog.plontology.payloads.RepositorySearchRequest;
import com.pilog.plontology.service.IPilogRepository;
import com.pilog.plontology.service.IPilogRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class PilogRepositoryImpl implements IPilogRepository {

    @Autowired
    private IPilogRepositoryService pilogRepositorySearch;

    @Autowired
    private WSAccessHistory wsAccessHistory;

    private static final String WS_NAME = "PiLogRepository";
    private static final Logger logger = Logger.getLogger(PilogRepositoryImpl.class.getName());

    public String searchRepository(RepositorySearchRequest request) {
        String responseData = "";

        try {
            String validatorMessage = wsAccessHistory.validateSubscription(request.getOrgn_id(), WS_NAME);

            if (!validatorMessage.equals("")) {
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("responseData", new ArrayList<>());
                responseMap.put("message", validatorMessage);
                responseMap.put("wsstatus", "failure");

                responseData = mapToJson(responseMap);
            } else {
                responseData = pilogRepositorySearch.searchRepository(request);
            }
        } catch (Exception e) {
            logger.severe("Error processing JSON");
        }
        return responseData;
    }

    private String mapToJson(Map<String, Object> map) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            logger.severe("Error processing JSON");
            return "{\"error\": \"Error processing JSON\"}";
        }
    }
}


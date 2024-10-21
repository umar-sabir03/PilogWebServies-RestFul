package com.pilog.plontology.controller;


import com.pilog.plontology.payloads.SAPCharacteristicsERP;
import com.pilog.plontology.payloads.SearchSAPCharacteristicsReqDTO;
import com.pilog.plontology.service.ISapCharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

    @RestController
    @RequestMapping("/SAPCharacteristics/Service")
    public class SAPCharacteristicsController {
        @Autowired
        private ISapCharacteristicsService sapCharacteristicsService;

        @GetMapping("/search")
        public List<SAPCharacteristicsERP> searchSAPCharacteristics(
              @RequestBody SearchSAPCharacteristicsReqDTO searchSAPCharacteristicsReqDTO) {

            List<SAPCharacteristicsERP> sapCharacteristicsERPS = sapCharacteristicsService.searchSAPCharacteristics(searchSAPCharacteristicsReqDTO);

            return sapCharacteristicsERPS;
        }
    }



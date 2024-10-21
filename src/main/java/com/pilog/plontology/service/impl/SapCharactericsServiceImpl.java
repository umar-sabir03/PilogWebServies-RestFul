package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.SAPCharacteristicsERP;
import com.pilog.plontology.payloads.SearchSAPCharacteristicsReqDTO;
import com.pilog.plontology.repository.pprm.SapCharacteristicsRepository;
import com.pilog.plontology.service.ISapCharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SapCharactericsServiceImpl implements ISapCharacteristicsService {
    @Autowired
    private SapCharacteristicsRepository sapCharacteristicsRepository;
    @Override
    public List<SAPCharacteristicsERP> searchSAPCharacteristics(SearchSAPCharacteristicsReqDTO searchSAPCharacteristicsReqDTO) {

        List<SAPCharacteristicsERP> sapCharacteristicsERPS = sapCharacteristicsRepository.searchSAPCharacteristics(searchSAPCharacteristicsReqDTO);
        return sapCharacteristicsERPS;
    }
}

package com.pilog.plontology.service;

import com.pilog.plontology.payloads.SAPCharacteristicsERP;
import com.pilog.plontology.payloads.SearchSAPCharacteristicsReqDTO;

import java.util.List;

public interface ISapCharacteristicsService {
     List<SAPCharacteristicsERP> searchSAPCharacteristics(SearchSAPCharacteristicsReqDTO searchSAPCharacteristicsReqDTO);
}

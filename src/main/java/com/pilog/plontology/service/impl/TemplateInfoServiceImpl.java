package com.pilog.plontology.service.impl;

import com.pilog.plontology.exception.BadRequestException;
import com.pilog.plontology.payloads.TemplateInfoData;
import com.pilog.plontology.payloads.TemplateInfoResponse;
import com.pilog.plontology.repository.apexqa.TemplateInfoRepository;
import com.pilog.plontology.service.ITemplateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateInfoServiceImpl implements ITemplateInfoService {
    @Autowired
    private TemplateInfoRepository templateInfoRepository;
    @Override
    public TemplateInfoResponse fetch(String term, String orgId, String isNested) {
        TemplateInfoResponse result=new TemplateInfoResponse();
        if (orgId == null || orgId.isEmpty()) {
            orgId = "E6EE49F8383C494098B18D06C64DDFF0";
        }

        List<Object[]> resultList = templateInfoRepository.fetchTemplates(term, orgId);

        if (resultList.isEmpty())
            throw new BadRequestException(HttpStatus.NO_CONTENT,"NO Data Found","no.data.found");
        List<TemplateInfoData> templateInfoData = convertToTemplateInfoData(resultList);
        result.setTemplateInfoDataList(templateInfoData);
        return result;
    }

    @Override
    public TemplateInfoResponse fetchMLData(String term, String orgId, String isNested, String languageId) {
        if (orgId == null || orgId.isEmpty()) {
            orgId = "E6EE49F8383C494098B18D06C64DDFF0";
        }
        List<Object[]> resultList = templateInfoRepository.fetchMLDataRaw(term, orgId, languageId);
        if (resultList.isEmpty())
            throw new BadRequestException(HttpStatus.NO_CONTENT,"NO Data Found","no.data.found");
        TemplateInfoResponse response = new TemplateInfoResponse();
        List<TemplateInfoData> templateInfoDataList = convertToTemplateInfoData(resultList);
        response.setTemplateInfoDataList(templateInfoDataList);
        return response;
    }
    public TemplateInfoResponse fetchMLPropswithClassTerm(String term, String orgId, String languageId) {
        if (orgId == null || orgId.isEmpty()) {
            orgId = "E6EE49F8383C494098B18D06C64DDFF0";
        }
        List<Object[]> resultList = templateInfoRepository.fetchMLPropswithClassTermRaw(term, orgId, languageId);
        if (resultList.isEmpty())
            throw new BadRequestException(HttpStatus.NO_CONTENT,"NO Data Found","no.data.found");
        TemplateInfoResponse response = new TemplateInfoResponse();
        response.setTemplateInfoDataList(convertToTemplateInfoData(resultList));
        return response;
    }

     private List<TemplateInfoData> convertToTemplateInfoData(List<Object[]> resultList) {
        List<TemplateInfoData> templateInfoDataList = new ArrayList<>();

        resultList.forEach(row -> {
            TemplateInfoData templateInfoData = new TemplateInfoData();

            templateInfoData.setItemRef((String) row[0]); // ITEM_REF
            templateInfoData.setWord((String) row[1]); // WORD
            templateInfoData.setODef((String) row[2]); // O_DEF
            templateInfoData.setMandatoryInd((String) row[3]); // MANDATORY_IND
            templateInfoData.setOSeq((row[4]).toString()); // O_SEQ
            templateInfoData.setOStxtSeq((row[5]).toString()); // O_STXT_SEQ
            templateInfoData.setOAbbr((String) row[6]); // O_ABBR
            templateInfoData.setDatatypeRef((String) row[7]); // DATATYPE_REF
            templateInfoData.setConceptId((String) row[8]); // CONCEPTID
            templateInfoData.setHighLvlInd((String) row[9]); // HIGH_LVL_IND
            String UOM = (String) row[10];
            templateInfoData.setUom((UOM == null || UOM.isEmpty()) ? "N/A" : UOM); // UOM


            templateInfoDataList.add(templateInfoData);
        });

        return templateInfoDataList;
    }

}

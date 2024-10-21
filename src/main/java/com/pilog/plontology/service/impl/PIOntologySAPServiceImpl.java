package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.*;
import com.pilog.plontology.repository.piontosap.PIOntologySapRepository;
import com.pilog.plontology.service.IPIOntologySAPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PIOntologySAPServiceImpl implements IPIOntologySAPService {

    @Autowired
    private PIOntologySapRepository piOntologySapRepository;
    @Override
    public List<MTRLPPOTermDetail> getClassDetailData(ClassRequestDetailInfo request) {
        String className = (request.getClazz() == null) ? "" : request.getClazz();
        String locale = (request.getLocale() == null) ? "" : request.getLocale();
        String conceptType = (request.getConceptType() == null) ? "" : request.getConceptType();
        List<MTRLPPOTermDetail> detailList = piOntologySapRepository.getClassDetailData(className, locale, conceptType);
        return detailList;
    }

    @Override
    public List<MTRLPPOTermDetail> getClassSAPData(ClassRequestSAPDetailInfo request) {
        String className = (request.getClazz() == null) ? "" : request.getClazz();
        String locale = (request.getLocale() == null) ? "" : request.getLocale();
        String conceptType = (request.getConceptType() == null) ? "" : request.getConceptType();
        String conceptId = (request.getConceptId() == null) ? "" : request.getConceptId();
        List<MTRLPPOTermDetail> classSAPDataList = piOntologySapRepository.getClassSAPData(className, locale, conceptType, conceptId);

        return classSAPDataList;
    }

    @Override
    public List<MTRLPPOTermSimple> getClassSimpleData(ClassRequestDetailInfo request) {
        String className = (request.getClazz() == null) ? "" : request.getClazz();
        String locale = (request.getLocale() == null) ? "" : request.getLocale();
        String conceptType = (request.getConceptType() == null) ? "" : request.getConceptType();
        List<MTRLPPOTermSimple> classSimpleDataList = piOntologySapRepository.getClassSimpleData(className, locale, conceptType);

        return classSimpleDataList;
    }

    @Override
    public List<MTRLPPOTERMAdvanced> getClassAdvanceData(ClassRequesAdvancedInfo request) {
        String className = (request.getClazz() == null) ? "" : request.getClazz();
        String locale = (request.getLocale() == null) ? "" : request.getLocale();
        List<MTRLPPOTERMAdvanced> classAdvanceDataList = piOntologySapRepository.getClassAdvanceData(className, locale);
        return classAdvanceDataList;
    }
}

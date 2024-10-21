package com.pilog.plontology.repository.piontosap;

import com.pilog.plontology.payloads.MTRLPPOTERMAdvanced;
import com.pilog.plontology.payloads.MTRLPPOTermDetail;
import com.pilog.plontology.payloads.MTRLPPOTermSimple;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PIOntologySapRepository {
    @PersistenceContext(unitName = "piontosapEntityManagerFactory")
    private EntityManager piOntoSapEntityManager;

    @SuppressWarnings("unchecked")
    public List<MTRLPPOTermDetail> getClassDetailData(String className, String locale, String conceptType) {

        String sql = "SELECT ROWID, M.CLASS, M.OBJECT, M.QUALIFIER, M.CONCEPT_ID, " +
                "M.ABBREVIATION, M.DEFINITION, M.DOMAIN " +
                "FROM MTRL_PPO_TERM_DETAIL M " +
                "WHERE UPPER(M.CLASS) LIKE UPPER(:className) " +
                "AND UPPER(M.LOCALE) = UPPER(:locale) " +
                "AND UPPER(M.CONCEPT_TYPE) = UPPER(:conceptType)";

        List<Object[]> resultList = piOntoSapEntityManager.createNativeQuery(sql)
                .setParameter("className", "%" + className.toUpperCase() + "%")
                .setParameter("locale", locale.toUpperCase())
                .setParameter("conceptType", conceptType.toUpperCase())
                .getResultList();

        List<MTRLPPOTermDetail> detailList = new ArrayList<>();
        for (Object[] row : resultList) {
            MTRLPPOTermDetail detail = new MTRLPPOTermDetail();
            detail.setClazz((String) row[1]);
            detail.setObject((String) row[2]);
            detail.setQualifier((String) row[3]);
            detail.setConceptId((String) row[4]);
            detail.setAbbreviation((String) row[5]);
            detail.setDefinition((String) row[6]);
            detail.setDomain((String) row[7]);

            detailList.add(detail);
        }

        return detailList;
    }

    @SuppressWarnings("unchecked")
    public List<MTRLPPOTermDetail> getClassSAPData(String className, String locale, String conceptType, String conceptId) {

        String sql = "SELECT ROWID, M.CONCEPT_TYPE, M.CLASS, M.OBJECT, M.QUALIFIER, " +
                "M.CONCEPT_ID, M.ABBREVIATION, M.DEFINITION, M.DOMAIN " +
                "FROM MTRL_PPO_TERM_DETAIL M " +
                "WHERE UPPER(M.CLASS) LIKE UPPER(:className) " +
                "AND UPPER(M.LOCALE) = UPPER(:locale) " +
                "AND UPPER(M.CONCEPT_ID) LIKE UPPER(:conceptId) " +
                "AND UPPER(M.CONCEPT_TYPE) = UPPER(:conceptType)";

        List<Object[]> resultList = piOntoSapEntityManager.createNativeQuery(sql)
                .setParameter("className", "%" + className + "%")
                .setParameter("locale", locale)
                .setParameter("conceptId", "%" + conceptId + "%")
                .setParameter("conceptType", conceptType)
                .getResultList();

        List<MTRLPPOTermDetail> detailList = new ArrayList<>();

        if (!resultList.isEmpty()) {
            for (Object[] row : resultList) {
                MTRLPPOTermDetail sapDetail = new MTRLPPOTermDetail();
                sapDetail.setAbbreviation((String) row[6]);
                sapDetail.setClazz((String) row[2]);
                sapDetail.setConceptId((String) row[5]);
                sapDetail.setDefinition((String) row[7]);
                sapDetail.setDomain((String) row[8]);
                sapDetail.setObject((String) row[3]);
                sapDetail.setQualifier((String) row[4]);

                detailList.add(sapDetail);
            }
        }

        return detailList;
    }

    @SuppressWarnings("unchecked")
    public List<MTRLPPOTermSimple> getClassSimpleData(String className, String locale, String conceptType) {
        String sql = "SELECT ROWID, M.CLASS, M.OBJECT, M.QUALIFIER, M.CONCEPT_ID, M.CONCEPT_TYPE, " +
                "M.ABBREVIATION, M.LOCALE " +
                "FROM MDQM8_2QA.MTRL_PPO_TERM_SIMPLE M " +
                "WHERE UPPER(M.CLASS) LIKE UPPER(:className) " +
                "AND UPPER(M.LOCALE) = UPPER(:locale) " +
                "AND UPPER(M.CONCEPT_TYPE) = UPPER(:conceptType)";

        List<Object[]> resultList = piOntoSapEntityManager.createNativeQuery(sql)
                .setParameter("className", "%" + className.toUpperCase() + "%")
                .setParameter("locale", locale.toUpperCase())
                .setParameter("conceptType", conceptType.toUpperCase())
                .getResultList();

        List<MTRLPPOTermSimple> simpleList = new ArrayList<>();
        for (Object[] row : resultList) {
            MTRLPPOTermSimple simple = new MTRLPPOTermSimple();
            simple.setClazz((String) row[1]);
            simple.setObject((String) row[2]);
            simple.setQualifier((String) row[3]);
            simple.setConceptId((String) row[4]);
            simple.setConceptType((String) row[5]);
            simple.setClassAbbreviation((String) row[6]);
            simple.setLanguage((String) row[7]);

            simpleList.add(simple);
        }

        return simpleList;
    }

    @SuppressWarnings("unchecked")
    public List<MTRLPPOTERMAdvanced> getClassAdvanceData(String className, String locale) {

        String sql = "SELECT ROWID, M.CLASS, M.OBJECT, M.QUALIFIER, M.CONCEPT_ID, " +
                "M.ABBREVIATION, M.DEFINITION, M.DOMAIN, M.INDUSTRY, M.DISCIPLINE " +
                "FROM MTRL_PPO_TERM_ADVANCED M " +
                "WHERE UPPER(M.CLASS) LIKE UPPER(:className) " +
                "AND UPPER(M.LOCALE) = UPPER(:locale)";

        List<Object[]> resultList = piOntoSapEntityManager.createNativeQuery(sql)
                .setParameter("className", "%" + className.toUpperCase() + "%")
                .setParameter("locale", locale.toUpperCase())
                .getResultList();

        List<MTRLPPOTERMAdvanced> advancedList = new ArrayList<>();
        for (Object[] row : resultList) {
            MTRLPPOTERMAdvanced advanced = new MTRLPPOTERMAdvanced();
            advanced.setClazz((String) row[1]);
            advanced.setObject((String) row[2]);
            advanced.setQualifier((String) row[3]);
            advanced.setConceptId((String) row[4]);
            advanced.setAbbreviation((String) row[5]);
            advanced.setDefinition((String) row[6]);
            advanced.setDomain((String) row[7]);
            advanced.setIndustry((String) row[8]);
            advanced.setDiscipline((String) row[9]);

            advancedList.add(advanced);
        }
        return advancedList;
    }
}

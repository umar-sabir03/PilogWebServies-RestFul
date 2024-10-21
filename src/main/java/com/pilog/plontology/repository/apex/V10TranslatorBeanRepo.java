package com.pilog.plontology.repository.apex;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class V10TranslatorBeanRepo {
    @PersistenceContext(unitName = "apexEntityManagerFactory")
    private EntityManager entityManager;
    public List<Object[]> getPPOMlValuesList(String locale, List<String> termsList){
        String sqlPPO = "SELECT TERM, TRANS_TERM FROM V_PPO_VALUES_ML_NEW WHERE LOCALE = :locale AND TERM IN (:terms)";
        Query queryPPO = entityManager.createNativeQuery(sqlPPO);
        queryPPO.setParameter("locale", locale);
        queryPPO.setParameter("terms", termsList);
        List<Object[]> resultsPPO = queryPPO.getResultList();
        return resultsPPO;
    }

    public List<Object[]> getPROMlValuesList(String locale, List<String> termsList){
        String sqlPPO = "SELECT TERM, TRANS_TERM FROM V_PRO_VALUES_ML_NEW WHERE LOCALE = :locale AND TERM IN (:terms)";
        Query queryPPO = entityManager.createNativeQuery(sqlPPO);
        queryPPO.setParameter("locale", locale);
        queryPPO.setParameter("terms", termsList);
        List<Object[]> resultsPPR = queryPPO.getResultList();

        return resultsPPR;
    }

    public List<Object[]> getIEVValuesMl(String locale, List<String> termsList) {
        String sqlPPO = "SELECT TERM, TRANS_TERM FROM V_IEV_VALUES_ML WHERE LANGUAGE = :language AND TERM IN (:terms)";
        Query queryPPO = entityManager.createNativeQuery(sqlPPO);
        queryPPO.setParameter("locale", locale);
        queryPPO.setParameter("terms", termsList);
        List<Object[]> ievValues = queryPPO.getResultList();

        return ievValues;
    }
}

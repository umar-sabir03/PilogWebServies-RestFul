package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.OTerm;
import com.pilog.plontology.payloads.RequestDto;
import com.pilog.plontology.service.ISAPTranslationsService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class SAPTranslationsServiceImpl implements ISAPTranslationsService {
    @PersistenceContext(unitName = "piontosapEntityManagerFactory")
    private EntityManager piontosapEntityManager;
    @Override
    @SuppressWarnings("unchecked")
    public List<OTerm> search(RequestDto request) {
         List<OTerm> response=new ArrayList<>();
        String sql = "SELECT TRANS,LOCALE FROM MTRL_PPO_TERM_SIMPLE_TRANS WHERE UPPER(CLASS) = UPPER(:term) AND CONCEPT_TYPE = 'CLASS'";
        Query nativeQuery = piontosapEntityManager.createNativeQuery(sql, OTerm.class);

        nativeQuery.setParameter("term", request.getTerm());

        List<Object[]> resultList = nativeQuery.getResultList();
        for(Object[] result:resultList){
            OTerm oTerm=new OTerm();
            oTerm.setNterm((String) result[0]);
            oTerm.setLocale((String) result[1]);
            response.add(oTerm);
        }
        return response;
    }

}

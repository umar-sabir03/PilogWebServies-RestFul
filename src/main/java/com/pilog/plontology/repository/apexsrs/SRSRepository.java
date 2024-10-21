package com.pilog.plontology.repository.apexsrs;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SRSRepository {

    @PersistenceContext(unitName = "apexsrsEntityManagerFactory")
    private EntityManager entityManager;

    public BigDecimal getCount(String countQuery) {
        return (BigDecimal) entityManager.createNativeQuery(countQuery).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getFinalResults(String finalQuery) {
        List<Tuple> resultListObj = entityManager.createNativeQuery(finalQuery, Tuple.class).getResultList();
        return convertTuplesToMap(resultListObj);
    }
    @Transactional
    public String getSRSId() {
        String srsId = "";
        try {
            Number seq = (Number) entityManager.createNativeQuery("SELECT SEQ_OPERATIONAL_SUPPORT_DESC.NEXTVAL FROM DUAL").getSingleResult();
            srsId = "SRS" + seq.longValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srsId;
    }

    private List<Map<String, Object>> convertTuplesToMap(List<Tuple> tuples) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple single : tuples) {
            Map<String, Object> tempMap = new HashMap<>();
            for (TupleElement<?> key : single.getElements()) {
                tempMap.put(key.getAlias(), single.get(key));
            }
            result.add(tempMap);
        }
        return result;
    }
}

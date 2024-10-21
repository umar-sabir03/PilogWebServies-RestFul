package com.pilog.plontology.repository.pprm;

import com.pilog.plontology.payloads.RepResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PRSapRepository {

    @PersistenceContext(unitName = "pprmEntityManagerFactory")
    private EntityManager entityManager;

    public List<RepResponse> executeDynamicQuery(String dynamicQuery) {

        Query nativeQuery = entityManager.createNativeQuery(dynamicQuery);

        List<Object[]> resultList = nativeQuery.getResultList();

        List<RepResponse> repResponseList = new ArrayList<>();
        for (Object[] row : resultList) {
            RepResponse repResponse = convertRowToRepResponse(row);
            repResponseList.add(repResponse);
        }

        return repResponseList;
    }

    private RepResponse convertRowToRepResponse(Object[] row) {
        RepResponse repResponse = new RepResponse();

        repResponse.setRecordNo((String) row[0]);
        repResponse.setErpsfd((String) row[1]);
        repResponse.setPurchase((String) row[2]);
        repResponse.setClassTerm((String) row[3]);
        repResponse.setLocale((String) row[4]);
        repResponse.setRegion((String) row[5]);
        repResponse.setUnspscCode((String) row[6]);
        repResponse.setUnspscDesc((String) row[7]);
        repResponse.setConceptId((String) row[8]);
        repResponse.setOrgnId((String) row[9]);
        repResponse.setQualityLevel((String) row[10]);
        repResponse.setTrustLevel((String) row[11]);
        repResponse.setActiveStatus((String) row[12]);
        repResponse.setCreateBy((String) row[13]);
        repResponse.setReferenceNo((String) row[14]);
        repResponse.setReferenceType((String) row[15]);
        repResponse.setVendorName((String) row[16]);
        repResponse.setStripReferenceNo((String) row[17]);
        repResponse.setResearchUrl((String) row[18]);
        repResponse.setUom((String) row[19]);
        repResponse.setMatlType((String) row[20]);

        return repResponse;
    }

    public Long executeDynamicCountQuery(String countQuery) {
        try {
            Query query = entityManager.createNativeQuery(countQuery);
            Object result = query.getSingleResult();
            if (result == null) {
                return 0L;
            }

            return ((Number) result).longValue();

        } catch (Exception e) {
            System.err.println("Error executing count query: " + e.getMessage());
            return 0L;
        }
    }

}

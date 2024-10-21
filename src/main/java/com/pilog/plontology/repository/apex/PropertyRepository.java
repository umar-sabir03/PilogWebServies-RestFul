package com.pilog.plontology.repository.apex;

import com.pilog.plontology.payloads.PropResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PropertyRepository {
    @PersistenceContext(unitName = "apexEntityManagerFactory")
    private EntityManager entityManager;
    @PersistenceContext(unitName = "apexqaEntityManagerFactory")
    private EntityManager entityManagerQA;

    public List<Object[]> getPropertiesByConceptId(String conceptId) {
        try{
        String sql = "SELECT ITEM_REF, WORD, O_DEF, MANDATORY_IND, O_SEQ, O_STXT_SEQ, O_ABBR, DATATYPE_REF, CONCEPTID " +
                "FROM FOM_VIE_PPO_TEMPLATES WHERE ITEM_REF = :itemRef";
        String itemRef = conceptId.substring(10, 16);
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("itemRef", itemRef);

        return query.getResultList();

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    public List<PropResponse> fetchProperties(String orgId, String classConceptId, String languageId) {
            String queryString = "SELECT ITEM_REF, WORD, O_DEF, MANDATORY_IND, O_SEQ, O_STXT_SEQ, O_ABBR, DATATYPE_REF, " +
                    "CONCEPTID, HIGH_LVL_IND, UOM, TERMID, DEFINITIONID, ABBREVIATIONID " +
                    "FROM MTRL_VIE_PPO_TEMPLATES " +
                    "WHERE LANGUAGEID = :languageId AND ORG_ID = :orgId AND CLASS_CONCEPT_ID = :classConceptId";

            Query query = entityManagerQA.createNativeQuery(queryString);
            query.setParameter("languageId", languageId);
            query.setParameter("orgId", orgId);
            query.setParameter("classConceptId", classConceptId);

            List<PropResponse> responseList = new ArrayList<>();
            List<Object[]> results = query.getResultList();

            for (Object[] row : results) {
                PropResponse propResponse = new PropResponse();
                propResponse.setItemRef((String) row[0]);
                propResponse.setWord((String) row[1]);
                propResponse.setODef((String) row[2]);
                propResponse.setMandatoryInd((String) row[3]);
                propResponse.setOSeq((String) row[4]);
                propResponse.setOStxtSeq((String) row[5]);
                propResponse.setOAbbr((String) row[6]);
                propResponse.setDatatypeRef((String) row[7]);
                propResponse.setConceptId((String) row[8]);
                propResponse.setHighLvlInd((String) row[9]);
                propResponse.setUom((String) row[10]);
                propResponse.setTermId((String) row[11]);
                propResponse.setDefinitionId((String) row[12]);
                propResponse.setAbbreviationId((String) row[13]);
                responseList.add(propResponse);
            }

            return responseList;
        }
}

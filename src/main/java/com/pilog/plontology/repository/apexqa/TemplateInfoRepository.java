package com.pilog.plontology.repository.apexqa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class TemplateInfoRepository {
    private static final Logger logger = LoggerFactory.getLogger(TemplateInfoRepository.class);
    @PersistenceContext(unitName = "apexqaEntityManagerFactory")
    private EntityManager entityManagerQA;

    public List<Object[]> fetchTemplates(String term, String orgId) {
        List<Object[]> results = new ArrayList<>();
        try {
            String queryStr = "SELECT ITEM_REF, WORD, O_DEF, MANDATORY_IND, O_SEQ, O_STXT_SEQ, " +
                    "O_ABBR, DATATYPE_REF, CONCEPTID, HIGH_LVL_IND, UOM " +
                    "FROM MTRL_VIE_PPO_TEMPLATES " +
                    "WHERE ORG_ID = :orgId " +
                    "AND UPPER(O_DESCRIPTOR) LIKE UPPER(:term)";

            Query query = entityManagerQA.createNativeQuery(queryStr);
            query.setParameter("orgId", orgId);
            query.setParameter("term", "%" + term + "%");

            results = query.getResultList();
        } catch (Exception ex) {
            logger.error("Error fetching templates for orgId: {}, term: {}", orgId, term, ex);
        }
        return results;
    }

    public List<Object[]> fetchMLDataRaw(String term, String orgId, String languageId) {
        try {
        String queryStr = "SELECT ITEM_REF, WORD, O_DEF, MANDATORY_IND, O_SEQ, O_STXT_SEQ, O_ABBR, DATATYPE_REF, " +
                "CONCEPTID, HIGH_LVL_IND, UOM FROM MTRL_VIE_PPO_TEMPLATES " +
                "WHERE LANGUAGEID = :languageId AND ORG_ID = :orgId AND CLASS_CONCEPT_ID = :term ORDER BY O_SEQ";

        Query query = entityManagerQA.createNativeQuery(queryStr);
        query.setParameter("languageId", languageId);
        query.setParameter("orgId", orgId);
        query.setParameter("term", term);

        return query.getResultList();
        } catch (Exception ex) {
            logger.error("Error fetching templates for orgId: {}, term: {}", orgId, term, ex);
        }
        return null;
    }

    public List<Object[]> fetchMLPropswithClassTermRaw(String term, String orgId, String languageId) {
  try{
        String queryStr = "SELECT ITEM_REF, WORD, O_DEF, MANDATORY_IND, O_SEQ, O_STXT_SEQ, O_ABBR, DATATYPE_REF, " +
                "CONCEPTID, HIGH_LVL_IND, UOM FROM MTRL_VIE_PPO_TEMPLATES " +
                "WHERE LANGUAGEID = :languageId AND ORG_ID = :orgId " +
                "AND UPPER(O_DESCRIPTOR) LIKE :term ORDER BY O_SEQ";

        Query query = entityManagerQA.createNativeQuery(queryStr);
        query.setParameter("languageId", languageId);
        query.setParameter("orgId", orgId);
        query.setParameter("term", term.toUpperCase());

        return query.getResultList();
       } catch (Exception ex) {
      logger.error("Error fetching templates for orgId: {}, term: {}", orgId, term, ex);
      }
        return null;
    }
}
package com.pilog.plontology.repository.apex;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Repository
public class TemplateRepository {

    @PersistenceContext(unitName = "apexEntityManagerFactory")
    private EntityManager entityManager;

    public List<Object[]> fetchTemplates(String orgnId, String classterm) {
        String queryString = "SELECT ITEM_REF, WORD, O_DEF, MANDATORY_IND, O_SEQ, O_STXT_SEQ, O_ABBR, DATATYPE_REF, CONCEPTID, HIGH_LVL_IND " +
                "FROM FOM_VIE_PPO_TEMPLATES WHERE ORG_ID = :orgnId AND UPPER(O_DESCRIPTOR) LIKE UPPER(:classterm)";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("orgnId", orgnId);
        query.setParameter("classterm", "%" + classterm + "%");

        return query.getResultList();
    }

    public boolean isTowel(String desc) {
        String queryString = "SELECT 1 FROM FOM_VIE_PPO_TEMPLATES WHERE UPPER(O_DESCRIPTOR) LIKE 'TOWEL' AND UPPER(WORD) LIKE :desc";

        Query query = entityManager.createNativeQuery(queryString);
        query.setParameter("desc", "%" + desc.toUpperCase() + "%");

        List<?> result = query.getResultList();
        return !result.isEmpty();
    }
}

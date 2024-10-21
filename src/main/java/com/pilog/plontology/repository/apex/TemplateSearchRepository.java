package com.pilog.plontology.repository.apex;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TemplateSearchRepository {

    @PersistenceContext(unitName = "apexEntityManagerFactory")
    private EntityManager entityManager;

    public List<Object[]> executeDynamicResultQuery(String resultQuery) {
        Query query = entityManager.createNativeQuery(resultQuery);
        return query.getResultList();
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

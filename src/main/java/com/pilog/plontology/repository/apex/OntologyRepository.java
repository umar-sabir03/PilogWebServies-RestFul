package com.pilog.plontology.repository.apex;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OntologyRepository {
    @PersistenceContext(unitName = "apexEntityManagerFactory")
    private EntityManager entityManager;

    public List<Object[]> findOntologyData(String classParam, String conceptIdParam) {
        String sql = buildQuery(classParam, conceptIdParam);

        Query query = entityManager.createNativeQuery(sql);

        return query.getResultList();
    }

    private String buildQuery(String classParam, String conceptIdParam) {
        String sql = "SELECT M.DR_ID, M.O_DESCRIPTOR, M.CONCEPTID, M.O_ABBR, M.UNSPSC_CODE, M.UNSPSC_DESCRIPTION " +
                "FROM FOM_VIE_PPO_CLASSES M " +
                "WHERE UPPER(M.O_DESCRIPTOR) = UPPER('" + classParam + "') ";

        if (!conceptIdParam.isEmpty()) {
            sql += "OR UPPER(M.CONCEPTID) LIKE UPPER('%" + conceptIdParam + "%') ";
        }

        sql += "AND M.LANGUAGEID = '1007-1#LG-000001#1' " +
                "GROUP BY M.DR_ID, M.O_DESCRIPTOR, M.CONCEPTID, M.O_ABBR, M.UNSPSC_CODE, M.UNSPSC_DESCRIPTION " +
                "ORDER BY M.O_DESCRIPTOR, M.CONCEPTID";

        return sql;
    }
}

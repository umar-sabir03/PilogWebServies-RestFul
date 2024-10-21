package com.pilog.plontology.repository.apex;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Base64;
import java.util.List;

@Repository
@Transactional
public class OrgTerminologyRepository {

    @PersistenceContext(unitName = "apexEntityManagerFactory")
    private EntityManager entityManager;

    public String getImageByClass(String classTerm) {
        try {
            String sql = "SELECT b.BLOB_CONTENT FROM org_terminology a, files b " +
                    "WHERE a.concept_id = b.record_id " +
                    "AND a.CONCEPT_ODT_ID = '222131C6D9204E55BC0FBDFC6B9E660F' " +
                    "AND a.ORG_ID = 'E6EE49F8383C494098B18D06C64DDFF0' " +
                    "AND b.ORG_ID = a.ORG_ID " +
                    "AND a.term = :term";

            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("term", classTerm);

            List<Object> results = query.getResultList();

            if (!results.isEmpty()) {
                Object result = results.get(0);

                if (result != null) {
                    byte[] blobContent = ((java.sql.Blob) result).getBytes(1, (int) ((java.sql.Blob) result).length());

                    if (blobContent != null && blobContent.length > 0) {
                        return "data:image/png;base64," + Base64.getEncoder().encodeToString(blobContent);
                    }
                }
            }

            return "images/no-image.jpg";
        } catch (Exception e) {
            e.printStackTrace();
            return "images/no-image.jpg";
        }
    }

}
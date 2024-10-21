package com.pilog.plontology.repository.apex;

import com.pilog.plontology.payloads.TemplateInsertResponse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Repository
public class TemplateInsertRepository {
    @PersistenceContext(unitName = "apexEntityManagerFactory")
    private EntityManager entityManager;

    public List<TemplateInsertResponse> getTemplateData(String orgId, String languageId, String domain, String conceptId) {
        List<TemplateInsertResponse> responseList = new ArrayList<>();

        String queryString = "SELECT DR_ID, CONCEPT_ID, TERM_ID, DEFINITION_ID, ABBREVIATION_ID, DEFINITION, BLOB_CONTENT, " +
                "LANGUAGE_ID, TERM, ABBREVIATION, UNSPSC_CODE, UNSPSC_DESCRIPTION, HL_CODE, CATEGORY, SUB_CATEGORY, " +
                "CAT_CODE, SUB_CAT_CODE, DOMAIN, INDUSTRY, DISCIPLINE, DISCIPLINE_ABBR FROM MTRL_SEARCH_TEMPLATES " +
                "WHERE ORG_ID = :orgId AND LANGUAGE_ID = :languageId AND DOMAIN = :domain AND CONCEPT_ID = :conceptId";

        Query query = entityManager.createNativeQuery(queryString, Object[].class);
        query.setParameter("orgId", orgId);
        query.setParameter("languageId", languageId); // can be different for each method
        query.setParameter("domain", domain);
        query.setParameter("conceptId", conceptId);

        List<Object[]> results = query.getResultList();
        results.forEach(row -> responseList.add(mapRowToTemplateResponse(row)));

        return responseList;
    }
    private TemplateInsertResponse mapRowToTemplateResponse(Object[] row) {
        TemplateInsertResponse templateResponse = new TemplateInsertResponse();

        templateResponse.setDrId((String) row[0]);            // DR_ID
        templateResponse.setConceptId((String) row[1]);       // CONCEPT_ID
        templateResponse.setTermId((String) row[2]);          // TERM_ID
        templateResponse.setDefinitionId((String) row[3]);    // DEFINITION_ID
        templateResponse.setAbbreviationId((String) row[4]);  // ABBREVIATION_ID
        templateResponse.setDefinition((String) row[5]);      // DEFINITION

        if (row[6] != null) {
            templateResponse.setBlobContent("data:image/png;base64," + Base64.getEncoder().encodeToString((byte[]) row[6])); // BLOB_CONTENT
        } else {
            templateResponse.setBlobContent("images/no-image.jpg");
        }

//        templateResponse.setLanguageId((String) row[7]);      // LANGUAGE_ID
        templateResponse.setTerm((String) row[8]);            // TERM
        templateResponse.setAbbreviation((String) row[9]);    // ABBREVIATION
        templateResponse.setUnspscCode((String) row[10]);     // UNSPSC_CODE
        templateResponse.setUnspscDescription((String) row[11]); // UNSPSC_DESCRIPTION
        templateResponse.setHlCode((String) row[12]);         // HL_CODE
        templateResponse.setCategory((String) row[13]);       // CATEGORY
        templateResponse.setSubcategory((String) row[14]);    // SUB_CATEGORY
        templateResponse.setCatCode((String) row[15]);        // CAT_CODE
        templateResponse.setSubCatCode((String) row[16]);     // SUB_CAT_CODE
//        templateResponse.setDomain((String) row[17]);         // DOMAIN
        templateResponse.setIndustry((String) row[18]);       // INDUSTRY
        templateResponse.setDiscipline((String) row[19]);     // DISCIPLINE
        templateResponse.setDisciplineAbbr((String) row[20]); // DISCIPLINE_ABBR

        return templateResponse;
    }



}

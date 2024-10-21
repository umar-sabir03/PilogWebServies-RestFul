package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.GenerateClassTermDTO;
import com.pilog.plontology.service.IClassIdService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Service
public class ClassIdServiceImpl implements IClassIdService {
    @PersistenceContext(unitName = "apexqaEntityManagerFactory")
    private EntityManager apexqaentityManager;
    @Override
    public String generateTerm(GenerateClassTermDTO generateClassTermDTO) {

        String message = "";
        try {
            if (generateClassTermDTO.getDomain() == null && "2A00A96C173342ABA2EBD2A697138CC5".equalsIgnoreCase(String.valueOf(generateClassTermDTO.getConceptTypeId()))) {
                message += ",domain";
            } else if (generateClassTermDTO.getConceptTypeId() == null) {
                message += ",conceptTypeId";
            } else if (generateClassTermDTO.getLanguageId() == null) {
                message += ",languageId";
            } else if (generateClassTermDTO.getOrgnId() == null) {
                message += ",orgnId";
            } else if (generateClassTermDTO.getTerm() == null) {
                message += ",term";
            } else if (generateClassTermDTO.getLanguageIRD1() == null) {
                message += ",languageIRD1";
            }
            String query = "SELECT SEQ_CNCPT_REG.NEXTVAL FROM DUAL";
            Integer crrId = (Integer) apexqaentityManager.createNativeQuery(query).getSingleResult();
            System.out.println("Generated CRR_ID: " + crrId);
            String insertQuery = "INSERT INTO CONCEPT_REGISTRATION_REQUEST(" +
                    "CONCEPT_TYPE_ID, LANGUAGE_ID, ORG_ID, TERM, TERM_ORIGINATOR_REF, DEFINITION, " +
                    "DEFINITION_ORIGINATOR_REF, ABBREVIATION, ABBREVIATION_ORIGINATOR_REF, LABEL, " +
                    "LABEL_ORIGINATOR_REF, LANGUAGE_IRDI, STATUS_REASON, DOMAIN, CRR_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            apexqaentityManager.createNativeQuery(insertQuery)
                    .setParameter(1, generateClassTermDTO.getConceptTypeId())
                    .setParameter(2, generateClassTermDTO.getLanguageId())
                    .setParameter(3, generateClassTermDTO.getOrgnId())
                    .setParameter(4, generateClassTermDTO.getTerm())
                    .setParameter(5, generateClassTermDTO.getTermOriginRef())
                    .setParameter(6, generateClassTermDTO.getDefinition())
                    .setParameter(7, generateClassTermDTO.getDefinitionOriginRef())
                    .setParameter(8, generateClassTermDTO.getAbbrevation())
                    .setParameter(9, generateClassTermDTO.getAbbrevationOriginRef())
                    .setParameter(10, generateClassTermDTO.getLabel())
                    .setParameter(11, generateClassTermDTO.getLabelOriginatorRef())
                    .setParameter(12, generateClassTermDTO.getLanguageIRD1())
                    .setParameter(13, generateClassTermDTO.getStatusReason())
                    .setParameter(14, generateClassTermDTO.getDomain())
                    .setParameter(15, crrId)
                    .executeUpdate();

            message = "Created term successfully";
            message = updateCrrId(crrId);
            if (message == null || message.isEmpty() || "null".equalsIgnoreCase(generateClassTermDTO.getAbbrevation())) {
                message = "ConceptId is not generated, Please check in MDOM.";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            message = "Unable to create new term";
        }

        return message;
    }


    private String updateCrrId(Integer crrId) {
        String message = "";
        try {
            String updateQuery = "UPDATE CONCEPT_REGISTRATION_REQUEST SET STATUS_RC = 'APPROVED' WHERE CRR_ID = :crrId";
            apexqaentityManager.createNativeQuery(updateQuery)
                    .setParameter("crrId", crrId)
                    .executeUpdate();

            String selectQuery = "SELECT IRDI FROM CONCEPT_REGISTRATION_REQUEST WHERE CRR_ID = :crrId";
            message = (String) apexqaentityManager.createNativeQuery(selectQuery)
                    .setParameter("crrId", crrId)
                    .getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            message = "Error updating CRR ID: " + e.getMessage();
        }
        return message;
    }

}

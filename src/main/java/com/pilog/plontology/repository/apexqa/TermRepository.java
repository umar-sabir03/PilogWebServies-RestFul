package com.pilog.plontology.repository.apexqa;

import com.pilog.plontology.payloads.GenerateTermDTO;
import com.pilog.plontology.payloads.GenerateTermDrDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TermRepository {

    @PersistenceContext(unitName = "apexqaEntityManagerFactory")
    private EntityManager entityManager;

    public List<GenerateTermDTO> getTerms(String countQuery, String mainQuery) {
        Long count = (Long) entityManager.createNativeQuery(countQuery).getSingleResult();

        Query query = entityManager.createNativeQuery(mainQuery);

        List<Object[]> resultList = query.getResultList();

        List<GenerateTermDTO> regList = resultList.stream().map(result -> {
            GenerateTermDTO termData = new GenerateTermDTO();
            termData.setCrrId((String) result[1]); // Index 1 because index 0 is RNUM
            termData.setStatusRc((String) result[2]);
            termData.setStatusReason((String) result[3]);
            termData.setConceptTypeId((String) result[4]);
            termData.setOrgId((String) result[5]);
            termData.setLanguageId((String) result[6]);
            termData.setConceptId((String) result[7]);
            termData.setOdtId((String) result[8]);
            termData.setConceptOdtId((String) result[9]);
            termData.setTermId((String) result[10]);
            termData.setTerm((String) result[11]);
            termData.setTermOriginatorRef((String) result[12]);
            termData.setDefinitionId((String) result[13]);
            termData.setDefinition((String) result[14]);
            termData.setDefinitionOriginatorRef((String) result[15]);
            termData.setAbbreviationId((String) result[16]);
            termData.setAbbreviation((String) result[17]);
            termData.setAbbreviationOriginatorRef((String) result[18]);
            termData.setLabelId((String) result[19]);
            termData.setLabel((String) result[20]);
            termData.setLabelOriginatorRef((String) result[21]);
            termData.setSysCreatedBy((String) result[22]);
            termData.setSysCreatedOn((String) result[23]);
            termData.setSysUpdatedBy((String) result[24]);
            termData.setSysUpdatedOn((String) result[25]);
            termData.setOldLanguageId((String) result[26]);
            termData.setOldDefinition((String) result[27]);
            termData.setOldTerm((String) result[28]);
            termData.setComments((String) result[29]);
            termData.setOldAbbreviation((String) result[30]);
            termData.setOldLabel((String) result[31]);
            termData.setOriginatingOrgId((String) result[32]);
            termData.setDrId((String) result[33]);
            termData.setClassId((String) result[34]);
            termData.setPropertyId((String) result[35]);
            termData.setIrdi((String) result[36]);
            termData.setDomain((String) result[37]);
            termData.setLanguageIrdi((String) result[38]);
            termData.setGuidelines((String) result[39]);
            termData.setOldGuidelines((String) result[40]);
            termData.setTotalCount(count);
            return termData;
        }).collect(Collectors.toList());
        return regList;
    }

    public List<GenerateTermDrDTO> getDrTerms(String countQuery, String mainQuery) {
        Long count = (Long) entityManager.createNativeQuery(countQuery).getSingleResult();

        Query query = entityManager.createNativeQuery(mainQuery);

        List<Object[]> resultList = query.getResultList();

        List<GenerateTermDrDTO> regList = resultList.stream().map(result -> {
            GenerateTermDrDTO termData = new GenerateTermDrDTO();

            termData.setOrgId((String) result[1]);
            termData.setDrId((String) result[2]);
            termData.setSequenceExtNo((String) result[3]);
            termData.setConceptType((String) result[4]);
            termData.setClassConceptId((String) result[5]);
            termData.setClassTermId((String) result[6]);
            termData.setClassTerm((String) result[7]);
            termData.setClassAbbr((String) result[8]);
            termData.setDomain((String) result[9]);
            termData.setPropertyConceptId((String) result[10]);
            termData.setPropertyTerm((String) result[11]);
            termData.setPropertyTermId((String) result[12]);
            termData.setPropertyAbbr((String) result[13]);
            termData.setDefinitionId((String) result[14]);
            termData.setDefinition((String) result[15]);
            termData.setStatusReason((String) result[16]);
            termData.setTxnmyStatus((String) result[17]);
            termData.setShortSeq((String) result[18]);
            termData.setLongSeq((String) result[19]);
            termData.setRequiredFlag((String) result[20]);
            termData.setPdrFlag((String) result[21]);
            termData.setDataType((String) result[22]);
            termData.setDrivenBy((String) result[23]);
            termData.setLanguageId((String) result[24]);
            termData.setAttachId((String) result[25]);
            termData.setCreateDate((LocalDate) result[26]);
            termData.setCreateBy((String) result[27]);
            termData.setEditDate((LocalDate) result[28]);
            termData.setEditBy((String) result[29]);
            termData.setApproveDate((LocalDate) result[30]);
            termData.setApproveBy((String) result[31]);
            termData.setProcessedDate((LocalDate) result[32]);
            termData.setProcessedBy((String) result[33]);
            termData.setHighLevelId((String) result[34]);
            termData.setStxtFlag((String) result[35]);
            termData.setLtxtFlag((String) result[36]);
            termData.setLocale((String) result[37]);
            termData.setActiveFlag((String) result[38]);
            termData.setAuditId((String) result[39]);
            termData.setComments((String) result[40]);
            termData.setMessage((String) result[41]);
            termData.setMessageFlag((String) result[42]);
            termData.setTotalCount(Math.toIntExact(count));
            termData.setCopyFlag((String) result[43]);
            termData.setApproveFlag((String) result[44]);

            return termData;
        }).collect(Collectors.toList());
        return regList;
    }

}

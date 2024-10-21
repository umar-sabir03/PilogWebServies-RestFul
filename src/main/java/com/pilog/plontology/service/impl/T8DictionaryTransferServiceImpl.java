package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.DataRequirementDataRow;
import com.pilog.plontology.payloads.PreferenceOntologyDataRow;
import com.pilog.plontology.service.IT8DictionaryTransferService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class T8DictionaryTransferServiceImpl implements IT8DictionaryTransferService {
    @PersistenceContext(unitName = "")
    private EntityManager t8EntityManager;
    @Override
    public List<PreferenceOntologyDataRow> getPreferenceOntology(String orgID, String instance, String ssUsername) {
        List<PreferenceOntologyDataRow> ontologyDataList = new ArrayList<>();
        try{
        String sqlPreferenceOntology = "SELECT ORG_GUID, LANGUAGE_ID, PREFERRED_TERM_ID, PREFERRED_DEFINITION_ID, " +
                "PREFERRED_ABBREVIATION_ID, CONCEPT_ID, TYPE_RC, CONCEPT_TYPE, LANGUAGE, TERM, DEFINITION, " +
                "ABBREVIATION, CONCEPT_TYPE_ID, CAT_TYPE, LOCALE, UOM, UNSPSC_CDE, UNSPSC_DESC " +
                "FROM FOM_VIE_ONT_PREF_ONTOLOGY WHERE ORG_GUID = ? ";
              //SELECT * FROM FOM_VIE_ONT_PREF_ONTOLOGY  WHERE ORG_GUID = ? AND LANGUAGE_ID LIKE ?
        Query query = t8EntityManager.createNativeQuery(sqlPreferenceOntology);
        query.setParameter(1, orgID);

        List<Object[]> resultList = query.getResultList();

        for (Object[] row : resultList) {
            PreferenceOntologyDataRow dataRow = new PreferenceOntologyDataRow();
            dataRow.setOrgGUID((String) row[0]);
            dataRow.setLangaugeID((String) row[1]);
            dataRow.setPreferredTermID((String) row[2]);
            dataRow.setPreferredDefinitionID((String) row[3]);
            dataRow.setPreferredAbbreviationID((String) row[4]);
            dataRow.setConceptID((String) row[5]);
            dataRow.setTypeRC((String) row[6]);
            dataRow.setConceptType((String) row[7]);
            dataRow.setLanguage((String) row[8]);
            dataRow.setTerm((String) row[9]);
            dataRow.setDefinition((String) row[10]);
            dataRow.setAbbreviation((String) row[11]);
            dataRow.setConceptTypeID((String) row[12]);
            dataRow.setCatType((String) row[13]);
            dataRow.setLocale((String) row[14]);
            dataRow.setUom((String) row[15]);
            dataRow.setUnspscCode((String) row[16]);
            dataRow.setUnspscDesc((String) row[17]);

            ontologyDataList.add(dataRow);
        }

    } catch (Exception ex) {
        Logger.getLogger(T8DictionaryTransferServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
    }

        return ontologyDataList;
}

    @Override
    public List<PreferenceOntologyDataRow> getPreferenceOntologyWithLanguageID(String orgID, String languageID, String instance, String ssUsername) {
        List<PreferenceOntologyDataRow> ontologyDataList = new ArrayList<>();
        try{
            String sqlPreferenceOntology = "SELECT ORG_GUID, LANGUAGE_ID, PREFERRED_TERM_ID, PREFERRED_DEFINITION_ID, " +
                    "PREFERRED_ABBREVIATION_ID, CONCEPT_ID, TYPE_RC, CONCEPT_TYPE, LANGUAGE, TERM, DEFINITION, " +
                    "ABBREVIATION, CONCEPT_TYPE_ID, CAT_TYPE, LOCALE, UOM, UNSPSC_CDE, UNSPSC_DESC " +
                    "FROM FOM_VIE_ONT_PREF_ONTOLOGY WHERE ORG_GUID = ? AND LANGUAGE_ID LIKE ?";
           //SELECT * FROM FOM_VIE_ONT_PREF_ONTOLOGY  WHERE ORG_GUID = ? AND LANGUAGE_ID LIKE ?
            Query query = t8EntityManager.createNativeQuery(sqlPreferenceOntology);
            query.setParameter(1, orgID);
            query.setParameter(2, languageID);


            List<Object[]> resultList = query.getResultList();

            for (Object[] row : resultList) {
                PreferenceOntologyDataRow dataRow = new PreferenceOntologyDataRow();
                dataRow.setOrgGUID((String) row[0]);
                dataRow.setLangaugeID((String) row[1]);
                dataRow.setPreferredTermID((String) row[2]);
                dataRow.setPreferredDefinitionID((String) row[3]);
                dataRow.setPreferredAbbreviationID((String) row[4]);
                dataRow.setConceptID((String) row[5]);
                dataRow.setTypeRC((String) row[6]);
                dataRow.setConceptType((String) row[7]);
                dataRow.setLanguage((String) row[8]);
                dataRow.setTerm((String) row[9]);
                dataRow.setDefinition((String) row[10]);
                dataRow.setAbbreviation((String) row[11]);
                dataRow.setConceptTypeID((String) row[12]);
                dataRow.setCatType((String) row[13]);
                dataRow.setLocale((String) row[14]);
                dataRow.setUom((String) row[15]);
                dataRow.setUnspscCode((String) row[16]);
                dataRow.setUnspscDesc((String) row[17]);

                ontologyDataList.add(dataRow);
            }

        } catch (Exception ex) {
            Logger.getLogger(T8DictionaryTransferServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ontologyDataList;
    }

    @Override
    public List<DataRequirementDataRow> getDataRequirementWithLanguageID(String orgID, String languageID, String instance, String ssUsername) {
        List<DataRequirementDataRow> dataRequirmentDataList = new ArrayList<>();
        try {

            String sqlPreferenceOntology = "SELECT ORG_GUID, ACTIVE_IND, CLASS_CONCEPT_ID, DATATYPE_REF, " +
                    "DUDE_MAND_IND, INCL_POD_IND, INCL_SFD_IND, ITEM_REF, ITEM_SEQ, O_PLC_IND, " +
                    "PROPERTY_CONCEPT_ID, POD_SEQ, SFD_SEQ, O_SPC_IND, VALUE_MAND_IND, LANGUAGE_ID, " +
                    "PREFERRED_TERM_ID, PREFERRED_DEFINITION_ID, PREFERRED_ABBREVIATION_ID, O_DESCRIPTOR, WORD " +
                    "FROM FOM_VIE_ONT_PREF_DATA_REQ WHERE ORG_GUID = ? AND LANGUAGE_ID LIKE ?";
                                         //SELECT ORG_GUID, ACTIVE_IND, CLASS_CONCEPT_ID, DATATYPE_REF,
            //      DUDE_MAND_IND, INCL_POD_IND, INCL_SFD_IND, ITEM_REF, ITEM_SEQ, O_PLC_IND,
            //       PROPERTY_CONCEPT_ID, POD_SEQ, SFD_SEQ, O_SPC_IND, VALUE_MAND_IND, LANGUAGE_ID,
            //       PREFERRED_TERM_ID, PREFERRED_DEFINITION_ID, PREFERRED_ABBREVIATION_ID, O_DESCRIPTOR, WORD
            //       FROM FOM_VIE_ONT_PREF_DATA_REQ WHERE ORG_GUID = ? AND LANGUAGE_ID LIKE ?";
//
            Query query = t8EntityManager.createNativeQuery(sqlPreferenceOntology);
            query.setParameter(1, orgID);
            query.setParameter(2, languageID);

            List<Object[]> resultList = query.getResultList();

            for (Object[] row : resultList) {
                DataRequirementDataRow dataRow = new DataRequirementDataRow();
                dataRow.setOrgID((String) row[0]);
                dataRow.setActiveInd((String) row[1]);
                dataRow.setClassConceptID((String) row[2]);
                dataRow.setDataTypeRef((String) row[3]);
                dataRow.setDudeMandatoryInd((String) row[4]);
                dataRow.setInclPOD((String) row[5]);
                dataRow.setInclSFD((String) row[6]);
                dataRow.setItemRef((String) row[7]);
                dataRow.setItemSeq((String) row[8]);
                dataRow.setPlaceInd((String) row[9]);
                dataRow.setPropertyConceptID((String) row[10]);
                dataRow.setSequencePOD(Integer.toString((Integer) row[11]));
                dataRow.setSequenceSFD(Integer.toString((Integer) row[12]));
                dataRow.setSpaceInd((String) row[13]);
                dataRow.setValueMandatoryInd((String) row[14]);
                dataRow.setLanguageID((String) row[15]);
                dataRow.setPreferredTermID((String) row[16]);
                dataRow.setPreferredDefinitionID((String) row[17]);
                dataRow.setPreferredAbbreviationID((String) row[18]);
                dataRow.setDescriptor((String) row[19]);
                dataRow.setProperty((String) row[20]);

                dataRequirmentDataList.add(dataRow);
            }
        } catch (Exception ex) {
            Logger.getLogger(T8DictionaryTransferServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        updateDataRequirementDownloaded(orgID);
        return dataRequirmentDataList;
    }

    private void updateDataRequirementDownloaded(String orgID) {
        try {
         String sqlDataRequirement = "UPDATE DT_DR_CHANGES SET STATE = :newState WHERE ORG_ID = :orgId AND STATE = :oldState";

            Query query = t8EntityManager.createNativeQuery(sqlDataRequirement);
            query.setParameter("newState", "DL");
            query.setParameter("orgId", orgID);
            query.setParameter("oldState", "AV");

            query.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(T8DictionaryTransferServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @Override
    public String getDataRequirementWithLanguageIDCount(String orgID, String languageID, String instance, String ssUsername) {
        String count = "";
        try {
            String sqlDataRequirement = "SELECT COUNT(*) FROM DT_DR_CHANGES WHERE ORG_ID = :orgId AND STATE = :state";
            //                           SELECT COUNT(*) AS COUNT FROM DT_DR_CHANGES WHERE ORG_ID = ? AND STATE = ?
            Query query = t8EntityManager.createNativeQuery(sqlDataRequirement);
            query.setParameter("orgId", orgID);
            query.setParameter("state", "AV");

            Object result = query.getSingleResult();
            count = result != null ? result.toString() : "";

        } catch (Exception ex) {
            Logger.getLogger(T8DictionaryTransferServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        return count != null ? count : "0";
    }

    @Override
    public String getUoMWithLanguageIDCount(String orgID, String languageID, String instance, String ssUsername) {
        String count = "";
        try {
            String sqlDataRequirement = "SELECT COUNT(*) FROM ORG_TERMINOLOGY A, LANGUAGE B " +
                    "WHERE A.ORG_ID = :orgId " +
                    "AND CONCEPT_ODT_ID = '34F7CFAC5CED4D8394EB2CCA9896B01D' " +
                    "AND A.LANGUAGE_ID = B.LANGUAGE_ID " +
                    "AND B.IRDI LIKE :languageID";
                   //SELECT COUNT(*) AS COUNT FROM ORG_TERMINOLOGY A, LANGUAGE B
            //       WHERE A.ORG_ID = ? AND CONCEPT_ODT_ID = '34F7CFAC5CED4D8394EB2CCA9896B01D'
            //       AND A.LANGUAGE_ID = B.LANGUAGE_ID AND B.IRDI LIKE ?
            Query query = t8EntityManager.createNativeQuery(sqlDataRequirement);
            query.setParameter("orgId", orgID);
            query.setParameter("languageID", languageID);

            Object result = query.getSingleResult();
            count = result != null ? result.toString() : "";

        } catch (Exception ex) {
            Logger.getLogger(T8DictionaryTransferServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return count != null ? count : "0";
    }

    @Override
    public String getTranslationsCount(String orgID, String languageID, String instance, String ssUsername) {
        String count = "";
        try {
            String sqlDataRequirement = "SELECT COUNT(*) FROM ORG_TERMINOLOGY A, LANGUAGE B " +
                    "WHERE A.ORG_ID = :orgId " +
                    "AND CONCEPT_ODT_ID IN ('222131C6D9204E55BC0FBDFC6B9E660F', " +
                    "'B9017C1220E74530BB881B96C31A1E75', '34F7CFAC5CED4D8394EB2CCA9896B01D') " +
                    "AND A.LANGUAGE_ID = B.LANGUAGE_ID " +
                    "AND B.IRDI <> :languageID";
                //SELECT COUNT(*) AS COUNT FROM ORG_TERMINOLOGY A, LANGUAGE B WHERE A.ORG_ID = ?
            // AND CONCEPT_ODT_ID IN ('222131C6D9204E55BC0FBDFC6B9E660F','B9017C1220E74530BB881B96C31A1E75', '34F7CFAC5CED4D8394EB2CCA9896B01D')
            // AND A.LANGUAGE_ID = B.LANGUAGE_ID AND B.IRDI <> ?"
            Query query = t8EntityManager.createNativeQuery(sqlDataRequirement);
            query.setParameter("orgId", orgID);
            query.setParameter("languageID", languageID);

            Object result = query.getSingleResult();
            count = result != null ? result.toString() : "";

        } catch (Exception ex) {
            Logger.getLogger(T8DictionaryTransferServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        return count != null ? count : "0";
    }

    @Override
    public String getDataRequirementWithLanguageIDCountforInsert(String orgID, String languageID, String instance, String ssUsername) {
        String count = "";
        try {
            String sqlDataRequirement = "SELECT DR_INSERT_CNT FROM ORG_DR_COUNT WHERE ORG_ID = :orgId AND LANGUAGE_ID LIKE :languageID";
                                       //SELECT DR_INSERT_CNT FROM ORG_DR_COUNT WHERE ORG_ID = ? AND LANGUAGE_ID LIKE ?
            Query query = t8EntityManager.createNativeQuery(sqlDataRequirement);
            query.setParameter("orgId", orgID);
            query.setParameter("languageID", languageID);

            Object result = query.getSingleResult();
            count = result != null ? result.toString() : "";

        } catch (Exception ex) {
            Logger.getLogger(T8DictionaryTransferServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count != null ? count : "0";
    }

    @Override
    public String getDataRequirementWithLanguageIDCountforDelete(String orgID, String languageID, String instance, String ssUsername) {
        String count = "";
        try {

            String sqlDataRequirement = "SELECT DR_DELETE_CNT FROM ORG_DR_COUNT WHERE ORG_ID = :orgId AND LANGUAGE_ID LIKE :languageID";
                                          //SELECT DR_DELETE_CNT FROM ORG_DR_COUNT WHERE ORG_ID = ? AND LANGUAGE_ID LIKE ?
            Query query = t8EntityManager.createNativeQuery(sqlDataRequirement);
            query.setParameter("orgId", orgID);
            query.setParameter("languageID", languageID);

            Object result = query.getSingleResult();
            count = result != null ? result.toString() : "";

        } catch (Exception ex) {
            Logger.getLogger(T8DictionaryTransferServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count != null ? count : "0";
    }
}

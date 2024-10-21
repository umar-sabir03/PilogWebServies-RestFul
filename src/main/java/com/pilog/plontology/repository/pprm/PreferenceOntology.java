//package com.pilog.plontology.repository.pprm;
//
//import com.pilog.plontology.beans.OntologyDataRow;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class PreferenceOntology {
//    public OntologyDataRow[] getDataSet(HashMap<String, Object> sessionHashMap)
//    {
//
//        OntologyDataRow[] dataSet = null;
//        List<OntologyDataRow> ontologyDataList = new ArrayList<>();
//
//
//        try
//        {
//            connection = JNDIConnectionManager.getConnection(sessionHashMap, "PreferenceOntology");
//            String sqlPreferenceOntology = "SELECT base.ORG_ID AS ORG_GUID, lang.IRDI AS LANGUAGE_ID, concept.IRDI AS CONCEPT_ID, "
//                    + "term.IRDI AS PREFERRED_TERM_ID, def.IRDI AS PREFERRED_DEFINITION_ID, abbr.IRDI AS PREFERRED_ABBREVIATION_ID, "
//                    + "'ONTOLOGY' AS TYPE_RC, ont_con_type.IRDI AS CONCEPT_TYPE_ID, ont_con_type.NAME AS CONCEPT_TYPE, "
//                    + "lang.NAME || ' ' || lang.COUNTRY_CODE AS LANGUAGE, base.TERM, base.ABBREVIATION, base.DEFINITION, "
//                    + "base.CONCEPT_ID AS MDOM_CONCEPT_ID, org_str.NAME AS ORG_NAME FROM ORG_TERMINOLOGY base "
//                    + "LEFT OUTER JOIN ONTOLOGY_TERM term ON base.CONCEPT_ID = term.CONCEPT_ID AND "
//                    + "base.TERM_ID = term.TERM_ID AND base.LANGUAGE_ID = term.LANGUAGE_ID "
//                    + "LEFT OUTER JOIN ONTOLOGY_DEFINITION def ON base.DEFINITION = def.DEFINITION AND base.CONCEPT_ID = def.CONCEPT_ID AND "
//                    + "base.DEFINITION_ID = def.DEFINITION_ID AND base.LANGUAGE_ID = def.LANGUAGE_ID "
//                    + "LEFT OUTER JOIN ONTOLOGY_ABBREVIATION abbr ON base.ABBREVIATION = abbr.ABBREVIATION AND term.TERM_ID = abbr.TERM_ID AND "
//                    + "base.ABBREVIATION_ID = abbr.ABBREVIATION_ID "
//                    + "LEFT OUTER JOIN ONTOLOGY_CONCEPT concept ON base.CONCEPT_ID = concept.CONCEPT_ID "
//                    + "LEFT OUTER JOIN LANGUAGE lang ON base.LANGUAGE_ID = lang.LANGUAGE_ID "
//                    + "LEFT OUTER JOIN ORG_STRUCTURE org_str ON base.ORG_ID = org_str.ORG_ID "
//                    + "LEFT OUTER JOIN ONTOLOGY_CONCEPT_TYPE ont_con_type ON concept.CONCEPT_TYPE_ID = ont_con_type.CONCEPT_TYPE_ID "
//                    + "WHERE base.ORG_ID = ? AND lang.IRDI LIKE ?";
//            stmtPreferenceOntology = connection.prepareStatement(sqlPreferenceOntology);
//            stmtPreferenceOntology.setString(1, (String) sessionHashMap.get("orgID"));
//            stmtPreferenceOntology.setString(2, (String) sessionHashMap.get("languageID"));
//            rsPreferenceOntology = stmtPreferenceOntology.executeQuery();
//
//            while (rsPreferenceOntology.next())
//            {
//                OntologyDataRow dataRow = new OntologyDataRow();
//                dataRow.setOrgGUID(rsPreferenceOntology.getString("ORG_GUID"));
//                dataRow.setLangaugeID(rsPreferenceOntology.getString("LANGUAGE_ID"));
//                dataRow.setPreferredTermID(rsPreferenceOntology.getString("PREFERRED_TERM_ID"));
//                dataRow.setPreferredDefinitionID(rsPreferenceOntology.getString("PREFERRED_DEFINITION_ID"));
//                dataRow.setPreferredAbbreviationID(rsPreferenceOntology.getString("PREFERRED_ABBREVIATION_ID"));
//                dataRow.setConceptID(rsPreferenceOntology.getString("CONCEPT_ID"));
//                dataRow.setTypeRC(rsPreferenceOntology.getString("TYPE_RC"));
//                dataRow.setConceptType(rsPreferenceOntology.getString("CONCEPT_TYPE"));
//                dataRow.setLanguage(rsPreferenceOntology.getString("LANGUAGE"));
//                dataRow.setTerm(rsPreferenceOntology.getString("TERM"));
//                dataRow.setDefinition(rsPreferenceOntology.getString("DEFINITION"));
//                dataRow.setAbbreviation(rsPreferenceOntology.getString("ABBREVIATION"));
//                dataRow.setConceptTypeID(rsPreferenceOntology.getString("CONCEPT_TYPE_ID"));
//                ontologyDataList.add(dataRow);
//            }
//
//            dataSet = new OntologyDataRow[ontologyDataList.size()];
//            dataSet = ontologyDataList.toArray(dataSet);
//        }
//        catch (Exception ex)
//        {
//            Logger.getLogger(PreferenceOntology.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        finally
//        {
//            JNDIConnectionManager.closeJDBCObjects(sessionHashMap, rsPreferenceOntology, stmtPreferenceOntology, connection);
//            return dataSet;
//        }
//    }
//}

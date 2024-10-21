package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.TemplateRequestDTO;
import com.pilog.plontology.payloads.TemplateSearchResponse;
import com.pilog.plontology.repository.apex.TemplateSearchRepository;
import com.pilog.plontology.service.ITemplateSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class TemplateSearchImpl implements ITemplateSearch {
    @Autowired
    private TemplateSearchRepository templateSearchRepository;
    @Override
    public List<TemplateSearchResponse> fetchClassTemplate(TemplateRequestDTO request) {

        List<TemplateSearchResponse> responseList = new ArrayList<>();

        try {
            String whereCondition = "";
            String countQuery = "";
            String resultQuery = "";
            Long count = 0L;
            String sortOrderCondition = "";
            System.out.println("request dto::::::::" + request.filterQuery);
            request.recordstartIndex = request.recordstartIndex == null ? "" : request.recordstartIndex;
            request.recordstartIndex = request.recordstartIndex.equalsIgnoreCase("?") ? "" : request.recordstartIndex;
            request.recordstartIndex = request.recordstartIndex.equalsIgnoreCase("") ? "0" : request.recordstartIndex;

            request.recordendIndex = request.recordendIndex == null ? "" : request.recordendIndex;
            request.recordendIndex = request.recordendIndex.equalsIgnoreCase("") ? "10" : request.recordendIndex;

            request.multilingualFlag = request.multilingualFlag == null ? "" : request.multilingualFlag;
            request.multilingualFlag = request.multilingualFlag.equalsIgnoreCase("") ? "N" : request.multilingualFlag;

            request.languageId = request.languageId == null ? "" : request.languageId;
            request.languageId = request.languageId.equalsIgnoreCase("?") ? "" : request.languageId;
            request.languageId = request.languageId.equalsIgnoreCase("") ? "1007-1#LG-000001#1" : request.languageId;

            request.orgnId = request.orgnId == null ? "" : request.orgnId;
            request.orgnId = request.orgnId.equalsIgnoreCase("?") ? "" : request.orgnId;
            request.orgnId = request.orgnId.equalsIgnoreCase("") ? "E6EE49F8383C494098B18D06C64DDFF0" : request.orgnId;

            request.filterCount = request.filterCount == null ? "" : request.filterCount;
            request.filterCount = request.filterCount.equalsIgnoreCase("?") ? "" : request.filterCount;
            request.filterCount = request.filterCount.equalsIgnoreCase("") ? "0" : request.filterCount;

            request.sortOrder = request.sortOrder == null ? "" : request.sortOrder;
            request.sortOrder = request.sortOrder.equalsIgnoreCase("?") ? "" : request.sortOrder;
            request.sortOrder = request.sortOrder.equalsIgnoreCase("") ? "asc" : request.sortOrder;

            request.sortField = request.sortField == null ? "" : request.sortField;
            request.sortField = request.sortField.equalsIgnoreCase("?") ? "" : request.sortField;
            request.sortField = request.sortField.equalsIgnoreCase("") ? "TERM" : request.sortField;

            request.filterQuery = request.filterQuery == null ? "" : request.filterQuery;
            request.filterQuery = request.filterQuery.equalsIgnoreCase("?") ? "" : request.filterQuery;
            request.filterQuery = request.filterQuery.equalsIgnoreCase("") ? "" : request.filterQuery;

            System.out.println("request.operator::::::::" + request.operator);
            System.out.println("request.TERM::::::::" + request.term);

            if (request.conceptId == null || !"Y".equalsIgnoreCase(request.conceptId)) {
                if (request.unspscCode != null && !"".equalsIgnoreCase(request.unspscCode)) {

                    if (request.operator != null && !"".equalsIgnoreCase(request.operator) && "IN".equalsIgnoreCase(request.operator)) {
                        if (request.unspscCode.startsWith("'")) {
                            request.unspscCode = request.unspscCode.toString();
                        } else {
                            request.unspscCode = "'" + request.unspscCode.replaceAll(",", "','") + "'";
                        }
                        request.unspscCode = request.unspscCode.replaceAll("'\\s{1,}", "'");
                        request.unspscCode = trimChar(request.unspscCode, '#');
                        whereCondition += "UNSPSC_CODE " + request.operator + " (" + request.unspscCode + ") AND ";
                    } else if (request.operator != null && !"".equalsIgnoreCase(request.operator) && ("EQUAL".equalsIgnoreCase(request.operator) || "=".equalsIgnoreCase(request.operator))) {
                        whereCondition += "UNSPSC_CODE " + request.operator + " '" + request.unspscCode + "' AND ";
                    } else if (request.operator != null && !"".equalsIgnoreCase(request.operator) && "LIKE".equalsIgnoreCase(request.operator)) {
                        whereCondition += "UNSPSC_CODE " + request.operator + " '" + request.unspscCode + "%' AND ";
                    }

                }
            }
            if (request.conceptId != null && !"".equalsIgnoreCase(request.conceptId) && !"Y".equalsIgnoreCase(request.conceptId)) {
                if (request.operator != null && !"".equalsIgnoreCase(request.operator) && "IN".equalsIgnoreCase(request.operator)) {
                    request.conceptId = "'" + request.conceptId.replaceAll(",", "','") + "'";
                    whereCondition += "CONCEPT_ID " + request.operator + " (" + request.conceptId + " ) AND ";
                }

            } else if (request.conceptId != null && "Y".equalsIgnoreCase(request.conceptId)) {
                System.out.println("inside conceptid:::" + request.conceptId + "" + request.operator + ":::::" + request.unspscCode);
                if (request.operator != null && !"".equalsIgnoreCase(request.operator) && "IN".equalsIgnoreCase(request.operator)) {
                    if (request.unspscCode.startsWith("'")) {
                        request.unspscCode = request.unspscCode.toString();
                    } else {
                        request.unspscCode = "'" + request.unspscCode.replaceAll("#", "','") + "'";
                    }
                    request.unspscCode = request.unspscCode.replaceAll("'\\s{1,}", "'");
                }
                System.out.println("unspscCode" + request.unspscCode);
                whereCondition += "CONCEPT_ID IN (SELECT CONCEPT_ID FROM MTRL_SEARCH_TEMPLATES WHERE UNSPSC_CODE IN(" + request.unspscCode + ")) AND";
            }
            if (request.term != null && !"".equalsIgnoreCase(request.term)) {
                if (request.operator != null && !"".equalsIgnoreCase(request.operator) && "LIKE".equalsIgnoreCase(request.operator)) {
                    request.term = trimChar(request.term, '#');
                    if (request.term.contains("OR")) {
                        whereCondition += "(TERM " + request.operator + request.term + ")";
                    } else if (request.term.endsWith(" TERM")) {

                        request.term = request.term.replace(" TERM", "");
                        System.out.println("else if" + request.term);
                        whereCondition += "TERM " + request.operator + request.term + " AND";
                    } else if (request.term.endsWith("%")) {
                        whereCondition += "TERM " + request.operator + " '" + request.term + "' AND ";
                    } else if (request.term.startsWith("'")) {
                        request.term = trimChar(request.term, '\'');
                        whereCondition += "TERM " + request.operator + " '" + request.term + "' AND ";
                    } else {
                        whereCondition += "TERM " + request.operator + " '%" + request.term + "%' AND ";
                    }
                } else if (request.operator != null && !"".equalsIgnoreCase(request.operator) && ("EQUALS".equalsIgnoreCase(request.operator) || "=".equalsIgnoreCase(request.operator))) {
                    if (request.term.endsWith("%")) {
                        whereCondition += "TERM LIKE '" + request.term + "' AND ";
                    } else if (request.term.startsWith("'")) {
                        whereCondition += "TERM LIKE " + request.term + " AND ";
                    } else {
                        whereCondition += "TERM LIKE '" + request.term + "' AND ";
                    }

                } else if (request.operator != null && !"".equalsIgnoreCase(request.operator) && "IN".equalsIgnoreCase(request.operator)) {
                    if (request.term.startsWith("'")) {
                        request.term = request.term.toString();
                    }
                    whereCondition += "TERM " + request.operator + " (" + request.term + ") AND ";
                } else if (request.operator != null && !"".equalsIgnoreCase(request.operator) && "BEGINNING WITH".equalsIgnoreCase(request.operator)) {
                    request.term = request.term.replaceAll("\'", "");
                    whereCondition += " TERM LIKE '" + request.term + "%' AND ";
                } else if (request.operator != null && !"".equalsIgnoreCase(request.operator) && "ENDING WITH".equalsIgnoreCase(request.operator)) {
                    request.term = request.term.replaceAll("\'", "");
                    whereCondition += " TERM LIKE '%" + request.term + "' AND ";
                } else if (request.operator != null && !"".equalsIgnoreCase(request.operator) && "IS NOT".equalsIgnoreCase(request.operator)) {
                    whereCondition += " TERM IS NOT NULL";
                } else if (request.operator != null && !"".equalsIgnoreCase(request.operator) && "IS".equalsIgnoreCase(request.operator)) {
                    whereCondition += " TERM IS NULL";
                } else if (request.operator != null && !"".equalsIgnoreCase(request.operator) && "NOT IN".equalsIgnoreCase(request.operator)) {
                    if (request.term.startsWith("'")) {
                        request.term = request.term.toString();
                    }
                    whereCondition += "TERM " + request.operator + " (" + request.term + ") AND ";
                } else if (request.operator != null && !"".equalsIgnoreCase(request.operator) && "NOT LIKE".equalsIgnoreCase(request.operator)) {
                    request.term = request.term.replaceAll("\'", "");
                    whereCondition += " TERM NOT LIKE '" + request.term + "' AND ";
                } else if (request.operator != null && !"".equalsIgnoreCase(request.operator) && "CONTAINING".equalsIgnoreCase(request.operator)) {
                    request.term = request.term.replaceAll("\'", "");
                    whereCondition += " TERM LIKE '%" + request.term + "%' AND ";
                }

            }
            whereCondition = trimAND(whereCondition);
            System.out.println("whereCondition" + whereCondition);
            String paginationCondition = "";
            if (request.sortOrder != null && (request.sortOrder.equalsIgnoreCase("asc") || request.sortOrder.equalsIgnoreCase("desc"))) {

                sortOrderCondition += "ORDER BY " + request.sortField + " " + request.sortOrder;
            }

            if (request.multilingualFlag != null && "Y".equalsIgnoreCase(request.multilingualFlag)) {

                resultQuery = "SELECT * FROM (SELECT ROWNUM AS RNUM,DR_ID,CONCEPT_ID, TERM_ID, DEFINITION_ID, ABBREVIATION_ID, DEFINITION, BLOB_CONTENT, TERM, ABBREVIATION, UNSPSC_CODE, UNSPSC_DESCRIPTION, CATEGORY,"
                        + " SUB_CATEGORY, CAT_CODE, SUB_CAT_CODE, INDUSTRY, DISCIPLINE,DISCIPLINE_ABBR,HL_CODE FROM MTRL_SEARCH_TEMPLATES WHERE ORG_ID = '" + request.orgnId + "'AND LANGUAGE_ID = '" + request.languageId + "' AND DOMAIN = '" + request.domain + "'";

                countQuery = "SELECT COUNT(*) FROM MTRL_SEARCH_TEMPLATES WHERE  ORG_ID = '" + request.orgnId + "' AND LANGUAGE_ID = '" + request.languageId + "' AND DOMAIN = '" + request.domain + "'";
                if (whereCondition != null && !"".equalsIgnoreCase(whereCondition)) {
                    resultQuery += " AND  " + whereCondition;

                    countQuery += " AND " + whereCondition ;
                }

                count = templateSearchRepository.executeDynamicCountQuery(countQuery);

                if (request.filterQuery != null && !"".equalsIgnoreCase(request.filterQuery)) {
                    resultQuery += " AND " + request.filterQuery;
                    countQuery += " AND " + request.filterQuery;
                }

                if (sortOrderCondition != null && !"".equalsIgnoreCase(sortOrderCondition)) {
                    resultQuery += " " + sortOrderCondition + ")";
                }

                resultQuery += " WHERE RNUM BETWEEN " + request.recordstartIndex + " AND " + request.recordendIndex;
                System.out.println("countQuery::1::" + countQuery);
                System.out.println("resultQuery::1::" + resultQuery);
                responseList = new ArrayList();


                System.out.println("count:::::" + count);
                List<Object[]> listResults = templateSearchRepository.executeDynamicResultQuery(resultQuery);
                for (int i = 0; i < listResults.size(); i++) {
                    Object[] row = listResults.get(i);

                    TemplateSearchResponse templateResponse = new TemplateSearchResponse();

                    if (i == 0) {
                        templateResponse.setCount(count);
                    }

                    templateResponse.setDrId((String) row[1]); // DR_ID
                    templateResponse.setConceptId((String) row[2]); // CONCEPT_ID
                    templateResponse.setTermId((String) row[3]); // TERM_ID
                    templateResponse.setDefinitionId((String) row[4]); // DEFINITION_ID
                    templateResponse.setAbbreviationId((String) row[5]); // ABBREVIATION_ID
                    templateResponse.setDefinition((String) row[6]); // DEFINITION

                    byte[] blobContent = (byte[]) row[7];
                    if (blobContent != null) {
                        String base64Image = Base64.getEncoder().encodeToString(blobContent); // Using java.util.Base64
                        templateResponse.setBlobContent("data:image/png;base64," + base64Image);
                    } else {
                        templateResponse.setBlobContent("images/no-image.jpg");
                    }

                    templateResponse.setTerm((String) row[8]); // TERM
                    templateResponse.setAbbreviation((String) row[9]); // ABBREVIATION
                    templateResponse.setUnspscCode((String) row[10]); // UNSPSC_CODE
                    templateResponse.setUnspscDescription((String) row[11]); // UNSPSC_DESCRIPTION
                    templateResponse.setCategory((String) row[12]); // CATEGORY
                    templateResponse.setSubcategory((String) row[13]); // SUB_CATEGORY
                    templateResponse.setCatCode((String) row[14]); // CAT_CODE
                    templateResponse.setSubCatCode((String) row[15]); // SUB_CAT_CODE
                    templateResponse.setIndustry((String) row[16]); // INDUSTRY
                    templateResponse.setDiscipline((String) row[17]); // DISCIPLINE
                    templateResponse.setDisciplineAbbr((String) row[18]); // DISCIPLINE_ABBR
                    templateResponse.setHlCode((String) row[19]); // HL_CODE
                    responseList.add(templateResponse);
                }
            } else {

                resultQuery = "SELECT * FROM(SELECT ROWNUM AS RNUM,DR_ID,CONCEPT_ID, TERM_ID, DEFINITION_ID, ABBREVIATION_ID, DEFINITION, BLOB_CONTENT, TERM, ABBREVIATION, UNSPSC_CODE, UNSPSC_DESCRIPTION, CATEGORY,"
                        + " SUB_CATEGORY, CAT_CODE, SUB_CAT_CODE, INDUSTRY, DISCIPLINE,DISCIPLINE_ABBR,HL_CODE FROM MTRL_SEARCH_TEMPLATES WHERE ORG_ID = '" + request.orgnId + "'AND LANGUAGE_ID = '1007-1#LG-000001#1' AND DOMAIN = '" + request.domain + "'";

                countQuery = "SELECT COUNT(*) FROM MTRL_SEARCH_TEMPLATES WHERE  ORG_ID = '" + request.orgnId + "' AND LANGUAGE_ID = '1007-1#LG-000001#1' AND DOMAIN = '" + request.domain + "'";
                if (whereCondition != null && !"".equalsIgnoreCase(whereCondition)) {
                    resultQuery += " AND  " + whereCondition;

                    countQuery += " AND " + whereCondition ;
                }
                if (request.filterQuery != null && !"".equalsIgnoreCase(request.filterQuery)) {
                    resultQuery += " AND " + request.filterQuery;
                    countQuery += " AND " + request.filterQuery;
                }

                if (sortOrderCondition != null && !"".equalsIgnoreCase(sortOrderCondition)) {
                    resultQuery += " " + sortOrderCondition + ")";
                }

                resultQuery += " WHERE RNUM BETWEEN " + request.recordstartIndex + " AND " + request.recordendIndex;

                System.out.println("countQuery:ENGLISH:1::" + countQuery);
                System.out.println("resultQuery:ENGLISH:1::" + resultQuery);

                count = templateSearchRepository.executeDynamicCountQuery(countQuery);

                System.out.println("count:::::" + count);
                List<Object[]> listResults = templateSearchRepository.executeDynamicResultQuery(resultQuery);
                for (int i = 0; i < listResults.size(); i++) {
                    Object[] row = listResults.get(i);
                    TemplateSearchResponse templateResponse = new TemplateSearchResponse();

                    if (i == 0) {
                        templateResponse.setCount((long) listResults.size());
                    }

                    templateResponse.setAbbreviation((String) row[9]); // ABBREVIATION
                    templateResponse.setAbbreviationId((String) row[5]); // ABBREVIATION_ID

                    byte[] blobContent = (byte[]) row[7]; // BLOB_CONTENT
                    if (blobContent != null) {
                        String base64Image = Base64.getEncoder().encodeToString(blobContent);
                        templateResponse.setBlobContent("data:image/png;base64," + base64Image);
                    } else {
                        templateResponse.setBlobContent("images/no-image.jpg");
                    }

                    templateResponse.setCatCode((String) row[13]); // CAT_CODE
                    templateResponse.setCategory((String) row[12]); // CATEGORY
                    templateResponse.setConceptId((String) row[2]); // CONCEPT_ID
                    templateResponse.setDefinition((String) row[6]); // DEFINITION
                    templateResponse.setSubCatCode((String) row[14]); // SUB_CAT_CODE
                    templateResponse.setSubcategory((String) row[11]); // SUB_CATEGORY
                    templateResponse.setTerm((String) row[8]); // TERM

                    responseList.add(templateResponse);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseList;
    }
    public String trimAND(String query) {

        try {
            if (query != null && !"".equalsIgnoreCase(query)) {
                query = query.trim().replaceAll("\\s{2,}", " ");

                if (query.length() == (query.lastIndexOf(" and") + 4)) {
                    query = query.substring(0, query.lastIndexOf(" and"));
                }
                if (query.length() == (query.lastIndexOf(" AND") + 4)) {
                    query = query.substring(0, query.lastIndexOf(" AND"));
                }

                query = query.replaceAll(" and and ", " and ");
                query = query.replaceAll(" AND AND ", " AND ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return query;
    }

    public String trimChar(String query, char character) {

        try {
            if (query != null && !"".equalsIgnoreCase(query)) {
                query = query.trim().replaceAll("\\s{2,}", " ");

                if (query.charAt(0) == character) {
                    query = query.substring(1);
                }

                if (query.charAt(query.length() - 1) == character) {
                    query = query.substring(0, query.length() - 1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return query;
    }
}

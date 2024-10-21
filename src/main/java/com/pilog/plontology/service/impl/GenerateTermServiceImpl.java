package com.pilog.plontology.service.impl;

import com.pilog.plontology.model.apexqa.AddDrProperty;
import com.pilog.plontology.model.apexqa.ConceptRegistrationRequest;
import com.pilog.plontology.payloads.GenerateFilterDTO;
import com.pilog.plontology.payloads.GenerateTermDTO;
import com.pilog.plontology.payloads.GenerateTermDrDTO;
import com.pilog.plontology.payloads.TaxonomyOperationRequestDto;
import com.pilog.plontology.repository.apexqa.AddDrPropertyRepository;
import com.pilog.plontology.repository.apexqa.ConceptRegistrationRequestRepository;
import com.pilog.plontology.repository.apexqa.DtDrChangesRepository;
import com.pilog.plontology.repository.apexqa.TermRepository;
import com.pilog.plontology.service.IGenerateTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenerateTermServiceImpl implements IGenerateTermService {

    @Autowired
    private ConceptRegistrationRequestRepository conceptRegistrationRequestRepository;

    @Autowired
    private AddDrPropertyRepository addDrPropertyRepository;
    @Autowired
    private TermRepository termRepository;
    @Autowired
    private DtDrChangesRepository dtDrChangesRepository;

    @PersistenceContext(unitName = "apexqaEntityManagerFactory")
    private EntityManager entityManager;
    @Override
    public String generateConceptId(TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        String resp="Failed to Insert Data";
        ConceptRegistrationRequest request = new ConceptRegistrationRequest();
        request.setConceptTypeId(taxonomyOperationRequestDto.getConceptTypeId());
        request.setLanguageId(taxonomyOperationRequestDto.getLanguageId());
        request.setOrgnId(taxonomyOperationRequestDto.getOrgnId());
        request.setTerm(taxonomyOperationRequestDto.getTerm());
        request.setTermOriginRef(taxonomyOperationRequestDto.getTermOriginRef());
        request.setDefinition(taxonomyOperationRequestDto.getDefinition());
        request.setDefinitionOriginRef(taxonomyOperationRequestDto.getDefinitionOriginRef());
        request.setAbbrevation(taxonomyOperationRequestDto.getAbbrevation());
        request.setAbbrevationOriginRef(taxonomyOperationRequestDto.getAbbrevationOriginRef());
        request.setLabel(taxonomyOperationRequestDto.getLabel());
        request.setLabelOriginatorRef(taxonomyOperationRequestDto.getLabelOriginatorRef());
        request.setSysCreatedBy(taxonomyOperationRequestDto.getSysCreatedBy());
        request.setLanguageIrd1(taxonomyOperationRequestDto.getLanguageIRD1());
        request.setStatusReason(taxonomyOperationRequestDto.getStatusReason());
        request.setDomain(taxonomyOperationRequestDto.getDomain());

        ConceptRegistrationRequest savedRequest = conceptRegistrationRequestRepository.save(request);
        if (savedRequest.getCrrId() > 0) {
            resp = "Created term successfully";
        }
        return resp;
    }

    @Override
    public List<GenerateTermDTO> generateConceptReg(TaxonomyOperationRequestDto request) {
         List<GenerateTermDTO> regList = new ArrayList();
        try{
            request.getGeneratePageBean().setRecordStartIndex(
                    request.getGeneratePageBean().getRecordStartIndex() == null ? "" :
                            request.getGeneratePageBean().getRecordStartIndex()
            );

            request.getGeneratePageBean().setRecordEndIndex(
                    request.getGeneratePageBean().getRecordEndIndex() == null ? "" :
                            request.getGeneratePageBean().getRecordEndIndex()
            );

            Integer recordStartIndex = Integer.parseInt(request.getGeneratePageBean().getRecordStartIndex());
            Integer recordEndIndex = Integer.parseInt(request.getGeneratePageBean().getRecordEndIndex());
            String projectionFields = request.projectionFields;
            List<GenerateFilterDTO>  filterData = request.getGeneratePageBean().getFilterData();
            System.out.println("projectFields::" + String.valueOf(projectionFields));
            projectionFields = projectionFields == null ? "" : projectionFields;

            if (projectionFields.trim().equalsIgnoreCase("")) {
                projectionFields = "CRR_ID, STATUS_RC, STATUS_REASON, CONCEPT_TYPE_ID, ORG_ID, LANGUAGE_ID, CONCEPT_ID, ODT_ID, CONCEPT_ODT_ID, "
                        + "TERM_ID, TERM, TERM_ORIGINATOR_REF, DEFINITION_ID, DEFINITION, DEFINITION_ORIGINATOR_REF, ABBREVIATION_ID, ABBREVIATION,"
                        + " ABBREVIATION_ORIGINATOR_REF, LABEL_ID, LABEL, LABEL_ORIGINATOR_REF, SYS_CREATED_BY, SYS_CREATED_ON, SYS_UPDATED_BY, "
                        + "SYS_UPDATED_ON, OLD_LANGUAGE_ID, OLD_DEFINITION, OLD_TERM, COMMENTS, OLD_ABBREVIATION, OLD_LABEL, ORIGINATING_ORG_ID, "
                        + "DR_ID, CLASS_ID, PROPERTY_ID, IRDI, DOMAIN, LANGUAGE_IRDI, GUIDELINES, OLD_GUIDELINES ";
            }

            String[] projectColsArray = projectionFields.split(",");
            String query ="";
            String countQuery ="";
            String queryOperator = "";
            String sortedDataField = "";
            String sortOrder = "";
            if (!query.contains("WHERE ")) {
                query = query + " WHERE ";
            }
            if (query.toUpperCase().contains("WHERE")) {
                query = query + " upper(STATUS_RC) =upper('SUBMITTED')";
                query = query + " AND upper(CONCEPT_TYPE_ID) =upper('2A00A96C173342ABA2EBD2A697138CC5')";
                query = query + " AND upper(ORG_ID) =upper('77C2E81D2EB144228D436C78F4806A8B')";

            }
            if(filterData != null && !filterData.isEmpty())
            {
                for (int i = 0; i < filterData.size(); i++) {
                    GenerateFilterDTO filterDTO = filterData.get(i);
                    String filterCol = filterDTO.getFilterColumn();
                    String filterCondition = "";
                    String filterValue = filterDTO.getFilterValue();
                    filterCondition = filterDTO.getFilterCondition() == null ? "CONTAINS" : String.valueOf(filterDTO.getFilterCondition());
                    if (query.toUpperCase().contains("WHERE")) {
                        queryOperator = " AND ";
                    } else {
                        queryOperator = " WHERE ";
                    }

                    if (filterCol.toUpperCase().contains("DATE")) {
                        filterValue = filterValue.substring(0, filterValue.indexOf("GMT") - 9).trim();
                        if (filterCondition.equalsIgnoreCase("LESS_THAN_OR_EQUAL")) {
                            filterCol = "TO_DATE(TO_CHAR(" + filterCol + ",'DY MON DD YYYY'), 'DY MON DD YYYY')";
                            filterValue = "TO_DATE('" + filterValue + "','DY MON DD YYYY')";
                            query += queryOperator +" " + filterCol + " <= " + filterValue + "";
                        } else if (filterCondition.equalsIgnoreCase("GREATER_THAN_OR_EQUAL")) {
                            filterCol = "TO_DATE(TO_CHAR(" + filterCol + ",'DY MON DD YYYY'), 'DY MON DD YYYY')";
                            filterValue = "TO_DATE('" + filterValue + "','DY MON DD YYYY')";
                            query += queryOperator+" " + filterCol + " >= " + filterValue + "";
                        }
                    }
                    else {
                        filterValue = filterValue;
                        switch (filterCondition) {
                            case "NOT_EMPTY":
                                query += queryOperator + filterCol + " <> ''";
                                break;
                            case "NOT_NULL":
                                query += queryOperator + filterCol + " NOT NULL";
                                break;
                            case "EMPTY":
                                query += queryOperator + filterCol + " = ''";
                                break;
                            case "NULL":
                                query += queryOperator + " " + filterCol + " IS NULL";
                                break;
                            case "CONTAINS_CASE_SENSITIVE":
                                query += queryOperator + "   " + filterCol + " LIKE '%" + filterValue + "%'";
                                break;
                            case "CONTAINS":
                                filterValue = filterValue.replaceAll("//s", "%") + "%";
                                query += queryOperator + " " + "upper("+filterCol+")" + " LIKE upper('%" + filterValue + "%')";
                                break;
                            case "DOES_NOT_CONTAIN_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " NOT LIKE '%" + filterValue + "%'";
                                break;
                            case "DOES_NOT_CONTAIN":
                                query += queryOperator + " " + filterCol + " NOT LIKE '%" + filterValue + "%'";
                                break;
                            case "EQUAL_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " = '" + filterValue + "'";
                                break;
                            case "EQUAL":
                                query += queryOperator + filterCol + " = '" + filterValue + "'";
                                break;
                            case "NOT_EQUAL_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " <> '" + filterValue + "'";
                                break;
                            case "NOT_EQUAL":
                                query += queryOperator + " " + filterCol + " <> '" + filterValue + "'";
                                break;
                            case "GREATER_THAN":
                                query += queryOperator + " " + filterCol + " > '" + filterValue + "'";
                                break;
                            case "LESS_THAN":
                                query += queryOperator + " " + filterCol + " < '" + filterValue + "'";
                                break;
                            case "GREATER_THAN_OR_EQUAL":
                                query += queryOperator + " " + filterCol + " >= '" + filterValue + "'";
                                break;
                            case "LESS_THAN_OR_EQUAL":
                                query += queryOperator + " " + filterCol + " <= '" + filterValue + "'";
                                break;
                            case "STARTS_WITH_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " LIKE '" + filterValue + "%'";
                                break;
                            case "STARTS_WITH":
                                query += queryOperator + " " + filterCol + " LIKE '" + filterValue + "%'";
                                break;
                            case "ENDS_WITH_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " LIKE '%" + filterValue + "'";
                                break;
                            case "ENDS_WITH":
                                query += queryOperator + " " + filterCol + " LIKE '%" + filterValue + "'";
                                break;
                        }
                    }

                }
            }
            if (!String.valueOf(request.getGeneratePageBean().getSortedDataField()).equalsIgnoreCase("")
                    && !String.valueOf(request.getGeneratePageBean().getSortedDataField()).equalsIgnoreCase("null")) {
                sortedDataField = (String) request.getGeneratePageBean().getSortedDataField();
                sortOrder = (String) request.getGeneratePageBean().getSortedDataField();
                if (sortedDataField != null && sortOrder != null && (sortOrder.equals("asc") || sortOrder.equals("desc"))) {
                    query = query + " order by " + sortedDataField + " " + sortOrder;
                }
            }

            countQuery = " select count(*) FROM CONCEPT_REGISTRATION_REQUEST REQUEST " + query;

            query = "select ROWNUM as RNUM," + projectionFields + " FROM CONCEPT_REGISTRATION_REQUEST REQUEST " + query;

            query = "select * from ( " + query + ") where RNUM between " + (recordStartIndex + 1) + " AND " + recordEndIndex;

            System.out.println("query:::::*****"+query);

            System.out.println("countQuery:::::*****"+countQuery);

           regList = termRepository.getTerms(countQuery, query);

        } catch (Exception e){
            e.printStackTrace();
        }

        return regList;
    }

    @Override
    public List<GenerateTermDTO> generateConceptApp(TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List appList = new ArrayList();
        try{

            String pageNum = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getPageNum())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("1");

            String pageSize = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getPageSize())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("10");

            String recordStartIndexStr = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getRecordStartIndex())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("0");

            String recordEndIndexStr = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getRecordEndIndex())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("10");

            Integer pageNumber = Integer.parseInt(pageNum);
            Integer pageSizeInt = Integer.parseInt(pageSize);
            Integer recordStartIndex = Integer.parseInt(recordStartIndexStr);
            Integer recordEndIndex = Integer.parseInt(recordEndIndexStr);

            String projectionFields = Optional.ofNullable(taxonomyOperationRequestDto.getProjectionFields())
                    .orElse("");

            List<GenerateFilterDTO> filterData = taxonomyOperationRequestDto.getGeneratePageBean().getFilterData();

            System.out.println("projectFields::" + projectionFields);

            if (projectionFields.trim().equalsIgnoreCase("")) {
                projectionFields = "CRR_ID, STATUS_RC, STATUS_REASON, CONCEPT_TYPE_ID, REQUEST.ORG_ID, LANGUAGE_ID, CONCEPT_ID, ODT_ID, CONCEPT_ODT_ID, "
                        + "TERM_ID, TERM, TERM_ORIGINATOR_REF, DEFINITION_ID, DEFINITION, DEFINITION_ORIGINATOR_REF, ABBREVIATION_ID, ABBREVIATION,"
                        + " ABBREVIATION_ORIGINATOR_REF, LABEL_ID, LABEL, LABEL_ORIGINATOR_REF, SYS_CREATED_BY, SYS_CREATED_ON, SYS_UPDATED_BY, "
                        + "SYS_UPDATED_ON, OLD_LANGUAGE_ID, OLD_DEFINITION, OLD_TERM, COMMENTS, OLD_ABBREVIATION, OLD_LABEL, ORIGINATING_ORG_ID, "
                        + "REQUEST.DR_ID, CLASS_ID, PROPERTY_ID, IRDI, DOMAIN, LANGUAGE_IRDI, GUIDELINES, OLD_GUIDELINES ";
            }

            String[] projectColsArray = projectionFields.split(",");
            String query ="";
            String countQuery ="";
            String queryOperator = "";
            String sortedDataField = "";
            String sortOrder = "";
            if (!query.contains("WHERE ")) {
                query = query + " WHERE ";
            }
            if (query.toUpperCase().contains("WHERE")) {
                query = query + " upper(STATUS_RC) =upper('APPROVED')";
                query = query + " AND upper(CONCEPT_TYPE_ID) =upper('2A00A96C173342ABA2EBD2A697138CC5')";
                query = query + " AND upper(REQUEST.ORG_ID) =upper('77C2E81D2EB144228D436C78F4806A8B')";
                query = query + " AND REQUEST.ORG_ID =CHANGES.ORG_ID";
                query = query + " AND REQUEST.DR_ID =CHANGES.DR_ID";
                query = query + " AND CHANGES.STATE =upper('AV')";

            }
            if(filterData != null && !filterData.isEmpty())
            {
                for (int i = 0; i < filterData.size(); i++) {
                    GenerateFilterDTO filterDTO = filterData.get(i);
                    String filterCol = filterDTO.getFilterColumn();
                    String filterCondition = "";
                    String filterValue = filterDTO.getFilterValue();
                    filterCondition = filterDTO.getFilterCondition() == null ? "CONTAINS" : String.valueOf(filterDTO.getFilterCondition());
                    if (query.toUpperCase().contains("WHERE")) {
                        queryOperator = " AND ";
                    } else {
                        queryOperator = " WHERE ";
                    }

                    if (filterCol.toUpperCase().contains("DATE")) {
                        filterValue = filterValue.substring(0, filterValue.indexOf("GMT") - 9).trim();
                        if (filterCondition.equalsIgnoreCase("LESS_THAN_OR_EQUAL")) {
                            filterCol = "TO_DATE(TO_CHAR(" + filterCol + ",'DY MON DD YYYY'), 'DY MON DD YYYY')";
                            filterValue = "TO_DATE('" + filterValue + "','DY MON DD YYYY')";
                            query += queryOperator +" " + filterCol + " <= " + filterValue + "";
//                               query += queryOperator + "   " + filterCol + " "
//                                          + " <= TO_DATE('" + filterValue + "','dd-MM-yyyy')";
                        } else if (filterCondition.equalsIgnoreCase("GREATER_THAN_OR_EQUAL")) {
                            filterCol = "TO_DATE(TO_CHAR(" + filterCol + ",'DY MON DD YYYY'), 'DY MON DD YYYY')";
                            filterValue = "TO_DATE('" + filterValue + "','DY MON DD YYYY')";
                            query += queryOperator+" " + filterCol + " >= " + filterValue + "";
//                                query += queryOperator + "   " + filterCol + " "
//                                         + " >= TO_DATE('" + filterValue + "','dd-MM-yyyy')";
                        }
                    }
                    else {

                        // filterValue = filterValue.toUpperCase();
                        filterValue = filterValue;
                        switch (filterCondition) {
                            case "NOT_EMPTY":
                                query += queryOperator + filterCol + " <> ''";
                                break;
                            case "NOT_NULL":
                                query += queryOperator + filterCol + " NOT NULL";
                                break;
                            case "EMPTY":
                                query += queryOperator + filterCol + " = ''";
                                break;
                            case "NULL":
                                query += queryOperator + " " + filterCol + " IS NULL";
                                break;
                            case "CONTAINS_CASE_SENSITIVE":
                                query += queryOperator + "   " + filterCol + " LIKE '%" + filterValue + "%'";
                                break;
                            case "CONTAINS":
                                filterValue = filterValue.replaceAll("//s", "%") + "%";
                                query += queryOperator + " " + "upper("+filterCol+")" + " LIKE upper('%" + filterValue + "%')";
                                break;
                            case "DOES_NOT_CONTAIN_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " NOT LIKE '%" + filterValue + "%'";
                                break;
                            case "DOES_NOT_CONTAIN":
                                query += queryOperator + " " + filterCol + " NOT LIKE '%" + filterValue + "%'";
                                break;
                            case "EQUAL_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " = '" + filterValue + "'";
                                break;
                            case "EQUAL":
                                query += queryOperator + filterCol + " = '" + filterValue + "'";
                                break;
                            case "NOT_EQUAL_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " <> '" + filterValue + "'";
                                break;
                            case "NOT_EQUAL":
                                query += queryOperator + " " + filterCol + " <> '" + filterValue + "'";
                                break;
                            case "GREATER_THAN":
                                query += queryOperator + " " + filterCol + " > '" + filterValue + "'";
                                break;
                            case "LESS_THAN":
                                query += queryOperator + " " + filterCol + " < '" + filterValue + "'";
                                break;
                            case "GREATER_THAN_OR_EQUAL":
                                query += queryOperator + " " + filterCol + " >= '" + filterValue + "'";
                                break;
                            case "LESS_THAN_OR_EQUAL":
                                query += queryOperator + " " + filterCol + " <= '" + filterValue + "'";
                                break;
                            case "STARTS_WITH_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " LIKE '" + filterValue + "%'";
                                break;
                            case "STARTS_WITH":
                                query += queryOperator + " " + filterCol + " LIKE '" + filterValue + "%'";
                                break;
                            case "ENDS_WITH_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " LIKE '%" + filterValue + "'";
                                break;
                            case "ENDS_WITH":
                                query += queryOperator + " " + filterCol + " LIKE '%" + filterValue + "'";
                                break;
                        }

                    }
                }
            }

            if (!String.valueOf(taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField()).equalsIgnoreCase("")
                    && !String.valueOf(taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField()).equalsIgnoreCase("null")) {
                sortedDataField = (String) taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField();
                //query = query + " order by id." + sortField;
                sortOrder = (String) taxonomyOperationRequestDto.getGeneratePageBean().getSortOrder();
                if (sortedDataField != null && sortOrder != null && (sortOrder.equals("asc") || sortOrder.equals("desc"))) {
                    query = query + " order by " + sortedDataField + " " + sortOrder;
                }
            }

            countQuery = " select count(*) FROM CONCEPT_REGISTRATION_REQUEST REQUEST, DT_DR_CHANGES CHANGES" + query;

            query = "select ROWNUM as RNUM," + projectionFields + " FROM CONCEPT_REGISTRATION_REQUEST REQUEST, DT_DR_CHANGES CHANGES " + query;

            query = "select * from ( " + query + ") where RNUM between " + (recordStartIndex + 1) + " AND " + recordEndIndex;

            System.out.println("query:::::*****"+query);

            appList = termRepository.getTerms(countQuery, query);

        } catch (Exception e){
            e.printStackTrace();
        }

        return appList;
    }

    @Override
    public List<GenerateTermDTO> generateConceptPropertyReg(TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List regList = new ArrayList();
        try{
        String pageNum = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getPageNum())
                .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                .orElse("1");

        String pageSize = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getPageSize())
                .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                .orElse("10");

        String recordStartIndexStr = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getRecordStartIndex())
                .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                .orElse("0");

        String recordEndIndexStr = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getRecordEndIndex())
                .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                .orElse("10");

        Integer pageNumber = Integer.parseInt(pageNum);
        Integer pageSizeInt = Integer.parseInt(pageSize);
        Integer recordStartIndex = Integer.parseInt(recordStartIndexStr);
        Integer recordEndIndex = Integer.parseInt(recordEndIndexStr);

        String projectionFields = Optional.ofNullable(taxonomyOperationRequestDto.getProjectionFields())
                .orElse("");

        List<GenerateFilterDTO> filterData = taxonomyOperationRequestDto.getGeneratePageBean().getFilterData();


        if (projectionFields.trim().equalsIgnoreCase("")) {
                projectionFields = "CRR_ID, STATUS_RC, STATUS_REASON, CONCEPT_TYPE_ID, ORG_ID, LANGUAGE_ID, CONCEPT_ID, ODT_ID, CONCEPT_ODT_ID, "
                        + "TERM_ID, TERM, TERM_ORIGINATOR_REF, DEFINITION_ID, DEFINITION, DEFINITION_ORIGINATOR_REF, ABBREVIATION_ID, ABBREVIATION,"
                        + " ABBREVIATION_ORIGINATOR_REF, LABEL_ID, LABEL, LABEL_ORIGINATOR_REF, SYS_CREATED_BY, SYS_CREATED_ON, SYS_UPDATED_BY, "
                        + "SYS_UPDATED_ON, OLD_LANGUAGE_ID, OLD_DEFINITION, OLD_TERM, COMMENTS, OLD_ABBREVIATION, OLD_LABEL, ORIGINATING_ORG_ID, "
                        + "DR_ID, CLASS_ID, PROPERTY_ID, IRDI, DOMAIN, LANGUAGE_IRDI, GUIDELINES, OLD_GUIDELINES ";
            }

            String[] projectColsArray = projectionFields.split(",");
            String query ="";
            String countQuery ="";
            String queryOperator = "";
            String sortedDataField = "";
            String sortOrder = "";
            if (!query.contains("WHERE ")) {
                query = query + " WHERE ";
            }
            if (query.toUpperCase().contains("WHERE")) {
                query = query + " upper(STATUS_RC) =upper('REGISTERED')";
                query = query + " AND upper(CONCEPT_TYPE_ID) =upper('3A05D5B39AE34F2C97B76D72965D7A1E')";
                query = query + " AND upper(ORG_ID) =upper('77C2E81D2EB144228D436C78F4806A8B')";

            }
            if(filterData != null && !filterData.isEmpty())
            {
                for (int i = 0; i < filterData.size(); i++) {
                    GenerateFilterDTO filterDTO = filterData.get(i);
                    String filterCol = filterDTO.getFilterColumn();
                    String filterCondition = "";
                    String filterValue = filterDTO.getFilterValue();
                    filterCondition = filterDTO.getFilterCondition() == null ? "CONTAINS" : String.valueOf(filterDTO.getFilterCondition());
                    if (query.toUpperCase().contains("WHERE")) {
                        queryOperator = " AND ";
                    } else {
                        queryOperator = " WHERE ";
                    }

                    if (filterCol.toUpperCase().contains("DATE")) {
                        filterValue = filterValue.substring(0, filterValue.indexOf("GMT") - 9).trim();
                        if (filterCondition.equalsIgnoreCase("LESS_THAN_OR_EQUAL")) {
                            filterCol = "TO_DATE(TO_CHAR(" + filterCol + ",'DY MON DD YYYY'), 'DY MON DD YYYY')";
                            filterValue = "TO_DATE('" + filterValue + "','DY MON DD YYYY')";
                            query += queryOperator +" " + filterCol + " <= " + filterValue + "";
//                               query += queryOperator + "   " + filterCol + " "
//                                          + " <= TO_DATE('" + filterValue + "','dd-MM-yyyy')";
                        } else if (filterCondition.equalsIgnoreCase("GREATER_THAN_OR_EQUAL")) {
                            filterCol = "TO_DATE(TO_CHAR(" + filterCol + ",'DY MON DD YYYY'), 'DY MON DD YYYY')";
                            filterValue = "TO_DATE('" + filterValue + "','DY MON DD YYYY')";
                            query += queryOperator+" " + filterCol + " >= " + filterValue + "";
//                                query += queryOperator + "   " + filterCol + " "
//                                         + " >= TO_DATE('" + filterValue + "','dd-MM-yyyy')";
                        }
                    }
                    else {

                        // filterValue = filterValue.toUpperCase();
                        filterValue = filterValue;
                        switch (filterCondition) {
                            case "NOT_EMPTY":
                                query += queryOperator + filterCol + " <> ''";
                                break;
                            case "NOT_NULL":
                                query += queryOperator + filterCol + " NOT NULL";
                                break;
                            case "EMPTY":
                                query += queryOperator + filterCol + " = ''";
                                break;
                            case "NULL":
                                query += queryOperator + " " + filterCol + " IS NULL";
                                break;
                            case "CONTAINS_CASE_SENSITIVE":
                                query += queryOperator + "   " + filterCol + " LIKE '%" + filterValue + "%'";
                                break;
                            case "CONTAINS":
                                filterValue = filterValue.replaceAll("//s", "%") + "%";
                                query += queryOperator + " " + "upper("+filterCol+")" + " LIKE upper('%" + filterValue + "%')";
                                break;
                            case "DOES_NOT_CONTAIN_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " NOT LIKE '%" + filterValue + "%'";
                                break;
                            case "DOES_NOT_CONTAIN":
                                query += queryOperator + " " + filterCol + " NOT LIKE '%" + filterValue + "%'";
                                break;
                            case "EQUAL_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " = '" + filterValue + "'";
                                break;
                            case "EQUAL":
                                query += queryOperator + filterCol + " = '" + filterValue + "'";
                                break;
                            case "NOT_EQUAL_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " <> '" + filterValue + "'";
                                break;
                            case "NOT_EQUAL":
                                query += queryOperator + " " + filterCol + " <> '" + filterValue + "'";
                                break;
                            case "GREATER_THAN":
                                query += queryOperator + " " + filterCol + " > '" + filterValue + "'";
                                break;
                            case "LESS_THAN":
                                query += queryOperator + " " + filterCol + " < '" + filterValue + "'";
                                break;
                            case "GREATER_THAN_OR_EQUAL":
                                query += queryOperator + " " + filterCol + " >= '" + filterValue + "'";
                                break;
                            case "LESS_THAN_OR_EQUAL":
                                query += queryOperator + " " + filterCol + " <= '" + filterValue + "'";
                                break;
                            case "STARTS_WITH_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " LIKE '" + filterValue + "%'";
                                break;
                            case "STARTS_WITH":
                                query += queryOperator + " " + filterCol + " LIKE '" + filterValue + "%'";
                                break;
                            case "ENDS_WITH_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " LIKE '%" + filterValue + "'";
                                break;
                            case "ENDS_WITH":
                                query += queryOperator + " " + filterCol + " LIKE '%" + filterValue + "'";
                                break;
                        }

                        //query = query + " and upper(" + searchField + ") like '" + searchValue.toUpperCase() + "'";
                    }

                }
            }

            if (!String.valueOf(taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField()).equalsIgnoreCase("")
                    && !String.valueOf(taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField()).equalsIgnoreCase("null")) {
                sortedDataField = (String) taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField();
                //query = query + " order by id." + sortField;
                sortOrder = (String) taxonomyOperationRequestDto.getGeneratePageBean().getSortOrder();
                if (sortedDataField != null && sortOrder != null && (sortOrder.equals("asc") || sortOrder.equals("desc"))) {
                    query = query + " order by " + sortedDataField + " " + sortOrder;
                }
            }

            countQuery = " select count(*) FROM CONCEPT_REGISTRATION_REQUEST " + query;

            query = "select ROWNUM as RNUM," + projectionFields + " FROM CONCEPT_REGISTRATION_REQUEST " + query;

            query = "select * from ( " + query + ") where RNUM between " + (recordStartIndex + 1) + " AND " + recordEndIndex;

            System.out.println("query:::::*****"+query);

        regList = termRepository.getTerms(countQuery, query);

        } catch (Exception e){
            e.printStackTrace();
        }

        return regList;
    }

    @Override
    public List<GenerateTermDTO> generateConceptPropertyApp(TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List regList = new ArrayList();
        try{
            String pageNum = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getPageNum())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("1");

            String pageSize = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getPageSize())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("10");

            String recordStartIndexStr = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getRecordStartIndex())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("0");

            String recordEndIndexStr = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getRecordEndIndex())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("10");

            Integer pageNumber = Integer.parseInt(pageNum);
            Integer pageSizeInt = Integer.parseInt(pageSize);
            Integer recordStartIndex = Integer.parseInt(recordStartIndexStr);
            Integer recordEndIndex = Integer.parseInt(recordEndIndexStr);

            String projectionFields = Optional.ofNullable(taxonomyOperationRequestDto.getProjectionFields())
                    .orElse("");

            List<GenerateFilterDTO> filterData = taxonomyOperationRequestDto.getGeneratePageBean().getFilterData();



            if (projectionFields.trim().equalsIgnoreCase("")) {
                projectionFields = "CRR_ID, STATUS_RC, STATUS_REASON, CONCEPT_TYPE_ID, ORG_ID, LANGUAGE_ID, CONCEPT_ID, ODT_ID, CONCEPT_ODT_ID, "
                        + "TERM_ID, TERM, TERM_ORIGINATOR_REF, DEFINITION_ID, DEFINITION, DEFINITION_ORIGINATOR_REF, ABBREVIATION_ID, ABBREVIATION,"
                        + " ABBREVIATION_ORIGINATOR_REF, LABEL_ID, LABEL, LABEL_ORIGINATOR_REF, SYS_CREATED_BY, SYS_CREATED_ON, SYS_UPDATED_BY, "
                        + "SYS_UPDATED_ON, OLD_LANGUAGE_ID, OLD_DEFINITION, OLD_TERM, COMMENTS, OLD_ABBREVIATION, OLD_LABEL, ORIGINATING_ORG_ID, "
                        + "DR_ID, CLASS_ID, PROPERTY_ID, IRDI, DOMAIN, LANGUAGE_IRDI, GUIDELINES, OLD_GUIDELINES ";
            }

            String[] projectColsArray = projectionFields.split(",");
            String query ="";
            String countQuery ="";
            String queryOperator = "";
            String sortedDataField = "";
            String sortOrder = "";
            if (!query.contains("WHERE ")) {
                query = query + " WHERE ";
            }
            if (query.toUpperCase().contains("WHERE")) {
                query = query + " upper(STATUS_RC) =upper('REGISTERED')";
                query = query + " AND upper(CONCEPT_TYPE_ID) =upper('3A05D5B39AE34F2C97B76D72965D7A1E')";
                query = query + " AND upper(ORG_ID) =upper('77C2E81D2EB144228D436C78F4806A8B')";

            }
            if(filterData != null && !filterData.isEmpty())
            {
                for (int i = 0; i < filterData.size(); i++) {
                    GenerateFilterDTO filterDTO = filterData.get(i);
                    String filterCol = filterDTO.getFilterColumn();
                    String filterCondition = "";
                    String filterValue = filterDTO.getFilterValue();
                    filterCondition = filterDTO.getFilterCondition() == null ? "CONTAINS" : String.valueOf(filterDTO.getFilterCondition());
                    if (query.toUpperCase().contains("WHERE")) {
                        queryOperator = " AND ";
                    } else {
                        queryOperator = " WHERE ";
                    }

                    if (filterCol.toUpperCase().contains("DATE")) {
                        filterValue = filterValue.substring(0, filterValue.indexOf("GMT") - 9).trim();
                        if (filterCondition.equalsIgnoreCase("LESS_THAN_OR_EQUAL")) {
                            filterCol = "TO_DATE(TO_CHAR(" + filterCol + ",'DY MON DD YYYY'), 'DY MON DD YYYY')";
                            filterValue = "TO_DATE('" + filterValue + "','DY MON DD YYYY')";
                            query += queryOperator +" " + filterCol + " <= " + filterValue + "";
//                               query += queryOperator + "   " + filterCol + " "
//                                          + " <= TO_DATE('" + filterValue + "','dd-MM-yyyy')";
                        } else if (filterCondition.equalsIgnoreCase("GREATER_THAN_OR_EQUAL")) {
                            filterCol = "TO_DATE(TO_CHAR(" + filterCol + ",'DY MON DD YYYY'), 'DY MON DD YYYY')";
                            filterValue = "TO_DATE('" + filterValue + "','DY MON DD YYYY')";
                            query += queryOperator+" " + filterCol + " >= " + filterValue + "";
//                                query += queryOperator + "   " + filterCol + " "
//                                         + " >= TO_DATE('" + filterValue + "','dd-MM-yyyy')";
                        }
                    }
                    else {

                        // filterValue = filterValue.toUpperCase();
                        filterValue = filterValue;
                        switch (filterCondition) {
                            case "NOT_EMPTY":
                                query += queryOperator + filterCol + " <> ''";
                                break;
                            case "NOT_NULL":
                                query += queryOperator + filterCol + " NOT NULL";
                                break;
                            case "EMPTY":
                                query += queryOperator + filterCol + " = ''";
                                break;
                            case "NULL":
                                query += queryOperator + " " + filterCol + " IS NULL";
                                break;
                            case "CONTAINS_CASE_SENSITIVE":
                                query += queryOperator + "   " + filterCol + " LIKE '%" + filterValue + "%'";
                                break;
                            case "CONTAINS":
                                filterValue = filterValue.replaceAll("//s", "%") + "%";
                                query += queryOperator + " " + "upper("+filterCol+")" + " LIKE upper('%" + filterValue + "%')";
                                break;
                            case "DOES_NOT_CONTAIN_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " NOT LIKE '%" + filterValue + "%'";
                                break;
                            case "DOES_NOT_CONTAIN":
                                query += queryOperator + " " + filterCol + " NOT LIKE '%" + filterValue + "%'";
                                break;
                            case "EQUAL_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " = '" + filterValue + "'";
                                break;
                            case "EQUAL":
                                query += queryOperator + filterCol + " = '" + filterValue + "'";
                                break;
                            case "NOT_EQUAL_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " <> '" + filterValue + "'";
                                break;
                            case "NOT_EQUAL":
                                query += queryOperator + " " + filterCol + " <> '" + filterValue + "'";
                                break;
                            case "GREATER_THAN":
                                query += queryOperator + " " + filterCol + " > '" + filterValue + "'";
                                break;
                            case "LESS_THAN":
                                query += queryOperator + " " + filterCol + " < '" + filterValue + "'";
                                break;
                            case "GREATER_THAN_OR_EQUAL":
                                query += queryOperator + " " + filterCol + " >= '" + filterValue + "'";
                                break;
                            case "LESS_THAN_OR_EQUAL":
                                query += queryOperator + " " + filterCol + " <= '" + filterValue + "'";
                                break;
                            case "STARTS_WITH_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " LIKE '" + filterValue + "%'";
                                break;
                            case "STARTS_WITH":
                                query += queryOperator + " " + filterCol + " LIKE '" + filterValue + "%'";
                                break;
                            case "ENDS_WITH_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " LIKE '%" + filterValue + "'";
                                break;
                            case "ENDS_WITH":
                                query += queryOperator + " " + filterCol + " LIKE '%" + filterValue + "'";
                                break;
                        }

                        //query = query + " and upper(" + searchField + ") like '" + searchValue.toUpperCase() + "'";
                    }

                }
            }

            if (!String.valueOf(taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField()).equalsIgnoreCase("")
                    && !String.valueOf(taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField()).equalsIgnoreCase("null")) {
                sortedDataField = (String) taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField();
                //query = query + " order by id." + sortField;
                sortOrder = (String) taxonomyOperationRequestDto.getGeneratePageBean().getSortOrder();
                if (sortedDataField != null && sortOrder != null && (sortOrder.equals("asc") || sortOrder.equals("desc"))) {
                    query = query + " order by " + sortedDataField + " " + sortOrder;
                }
            }

            countQuery = " select count(*) FROM CONCEPT_REGISTRATION_REQUEST " + query;

            query = "select ROWNUM as RNUM," + projectionFields + " FROM CONCEPT_REGISTRATION_REQUEST " + query;

            query = "select * from ( " + query + ") where RNUM between " + (recordStartIndex + 1) + " AND " + recordEndIndex;

            System.out.println("query:::::*****"+query);

            System.out.println("countQuery:::::*****"+countQuery);
            regList = termRepository.getTerms(countQuery, query);
        } catch (Exception e){
            e.printStackTrace();
        }

        return regList;

    }

    @Override
    public List<GenerateTermDTO> generateConceptDr(TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDTO> regList = new ArrayList<>();
        GenerateTermDTO termData = new GenerateTermDTO();
        boolean flag = false;

        try {
            ConceptRegistrationRequest request = new ConceptRegistrationRequest();
            request.setConceptTypeId(taxonomyOperationRequestDto.getConceptTypeId());
            request.setLanguageId(taxonomyOperationRequestDto.getLanguageId());
            request.setOrgnId(taxonomyOperationRequestDto.getOrgnId());
            request.setTerm(taxonomyOperationRequestDto.getTerm());
            request.setConceptId(taxonomyOperationRequestDto.getConceptTypeId());
            request.setDefinition(taxonomyOperationRequestDto.getDefinition());
            request.setAbbreviation(taxonomyOperationRequestDto.getAbbrevation());
            request.setLabel(taxonomyOperationRequestDto.getLabel());
            request.setLanguageIrd1(taxonomyOperationRequestDto.getLanguageIRD1());
            request.setStatusReason(taxonomyOperationRequestDto.getStatusReason());
            request.setDomain(taxonomyOperationRequestDto.getDomain());
            request.setDrId(taxonomyOperationRequestDto.getDrId());

            conceptRegistrationRequestRepository.save(request);
            termData.setMessage("Property term inserted successfully");
            flag = true;
            termData.setMessageFlag(Boolean.toString(flag));
        } catch (Exception e) {
            termData.setMessage("Property term not inserted");
            flag = false;
            termData.setMessageFlag(Boolean.toString(flag));
            e.printStackTrace();
        }

        regList.add(termData);
        return regList;
    }

    @Override
    public List<GenerateTermDTO> ConceptDelete(TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDTO> regList = new ArrayList<>();
        GenerateTermDTO termData = new GenerateTermDTO();
        boolean flag = false;

        try {
            ConceptRegistrationRequest request = new ConceptRegistrationRequest();
            request.setConceptTypeId(taxonomyOperationRequestDto.getConceptTypeId());
            request.setLanguageId(taxonomyOperationRequestDto.getLanguageId());
            request.setOrgnId(taxonomyOperationRequestDto.getOrgnId());
            request.setTerm(taxonomyOperationRequestDto.getTerm());
            request.setConceptId(taxonomyOperationRequestDto.getConceptTypeId());
            request.setDefinition(taxonomyOperationRequestDto.getDefinition());
            request.setAbbreviation(taxonomyOperationRequestDto.getAbbrevation());
            request.setLabel(taxonomyOperationRequestDto.getLabel());
            request.setLanguageIrd1(taxonomyOperationRequestDto.getLanguageIRD1());
            request.setStatusReason(taxonomyOperationRequestDto.getStatusReason());
            request.setDomain(taxonomyOperationRequestDto.getDomain());
            request.setDrId(taxonomyOperationRequestDto.getDrId());
            request.setSysCreatedBy(taxonomyOperationRequestDto.getSysCreatedBy());

            conceptRegistrationRequestRepository.save(request);

            termData.setMessage("Term Deletion Request Sent Successfully");
            flag = true;
            termData.setMessageFlag(Boolean.toString(flag));
        } catch (Exception e) {
            termData.setMessage("Term Deletion Request Sent Failed");
            flag = false;
            termData.setMessageFlag(Boolean.toString(flag));
            e.printStackTrace();
        }

        regList.add(termData);
        return regList;
    }

    @Override
    public List<GenerateTermDTO> ConceptPropDelete(TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDTO> regList = new ArrayList<>();
        GenerateTermDTO termData = new GenerateTermDTO();
        boolean flag = false;

        try {
            AddDrProperty property = new AddDrProperty();
            property.setStatusRc(taxonomyOperationRequestDto.getStatusRc());
            property.setStatusReason(taxonomyOperationRequestDto.getStatusReason());
            property.setDrId(taxonomyOperationRequestDto.getDrId());
            property.setLanguageId(taxonomyOperationRequestDto.getLanguageId());
            property.setOrgnId(taxonomyOperationRequestDto.getOrgnId());
            property.setManFlag(taxonomyOperationRequestDto.getManFlag());
            property.setPropSeq(taxonomyOperationRequestDto.getPropSeq());
            property.setDefinition(taxonomyOperationRequestDto.getDefinition());
            property.setDataType(taxonomyOperationRequestDto.getDataType());
            property.setPropName(taxonomyOperationRequestDto.getPropName());
            property.setPropInd(taxonomyOperationRequestDto.getPropIND());
            property.setSysCreatedBy(taxonomyOperationRequestDto.getSysCreatedBy());

            addDrPropertyRepository.save(property);

            termData.setMessage("Property term added successfully");
            flag = true;
            termData.setMessageFlag(Boolean.toString(flag));
        } catch (Exception e) {
            termData.setMessage("Property term addition failed");
            flag = false;
            termData.setMessageFlag(Boolean.toString(flag));
            e.printStackTrace();
        }

        regList.add(termData);
        return regList;
    }

    @Override
    public List<GenerateTermDTO> ConceptPropModify(TaxonomyOperationRequestDto requestDto) {
        List<GenerateTermDTO> regList = new ArrayList<>();
        GenerateTermDTO termData = new GenerateTermDTO();

        try {
            AddDrProperty property = new AddDrProperty();
            property.setStatusRc(requestDto.getStatusRc());
            property.setStatusReason(requestDto.getStatusReason());
            property.setDrId(requestDto.getDrId());
            property.setLanguageId(requestDto.getLanguageId());
            property.setOrgnId(requestDto.getOrgnId());
            property.setManFlag(requestDto.getManFlag());
            property.setPropSeq(requestDto.getPropSeq());
            property.setDefinition(requestDto.getDefinition());
            property.setDataType(requestDto.getDataType());
            property.setPropName(requestDto.getPropName());
            property.setPropInd(requestDto.getPropIND());
            property.setCreateDate(ZonedDateTime.parse(requestDto.getSysCreatedBy()));
            property.setCreateDate(ZonedDateTime.now());

            addDrPropertyRepository.save(property);

            termData.setMessage("Property term modify request sent successfully");
            termData.setMessageFlag(Boolean.toString(true));
            regList.add(termData);

        } catch (Exception e) {
            termData.setMessage("Property term modify operation failed");
            termData.setMessageFlag(Boolean.toString(false));
            regList.add(termData);
            e.printStackTrace();
        }

        return regList;
    }

    @Override
    public List<Object[]> generateConceptTxmnyProcess(TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<Object[]> procList = new ArrayList<>();

        try {
            procList = conceptRegistrationRequestRepository.findApprovedConcepts(
                    taxonomyOperationRequestDto.getConceptTypeId(),
                    taxonomyOperationRequestDto.getLanguageId(),
                    taxonomyOperationRequestDto.getOrgnId(),
                    taxonomyOperationRequestDto.getTerm()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return procList;
    }

    @Override
    public GenerateTermDrDTO getDtDrChangesUpdate(TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        GenerateTermDrDTO termData = new GenerateTermDrDTO();
        boolean flag = false;

        int updatedRows = dtDrChangesRepository.updateStateByOrgIdAndDrId(taxonomyOperationRequestDto.getDrId());

        if (updatedRows > 0) {
            termData.setMessage("Updated Successfully");
            flag = true;
        } else {
            termData.setMessage("Update Failed");
            flag = false;
        }

        termData.setMessageFlag(Boolean.toString(flag));
        return termData;
    }

    @Override
    public List<GenerateTermDrDTO> generateConceptDrProtyApp(TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List appList = new ArrayList();
        try {

            String pageNum = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getPageNum())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("1");

            String pageSize = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getPageSize())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("10");

            String recordStartIndexStr = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getRecordStartIndex())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("0");

            String recordEndIndexStr = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getRecordEndIndex())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("10");

            Integer pageNumber = Integer.parseInt(pageNum);
            Integer pageSizeInt = Integer.parseInt(pageSize);
            Integer recordStartIndex = Integer.parseInt(recordStartIndexStr);
            Integer recordEndIndex = Integer.parseInt(recordEndIndexStr);

            String projectionFields = Optional.ofNullable(taxonomyOperationRequestDto.getProjectionFields())
                    .orElse("");

            List<GenerateFilterDTO> filterData = taxonomyOperationRequestDto.getGeneratePageBean().getFilterData();


            if (projectionFields.trim().equalsIgnoreCase("")) {
                projectionFields = "ORG_ID, DR_ID, SEQUENCE_EXT_NO, CONCEPT_TYPE, CLASS_CONCEPT_ID, CLASS_TERM_ID, CLASS_TERM, CLASS_ABBR, DOMAIN, PROPERTY_CONCEPT_ID, "
                        + "PROPERTY_TERM_ID,PROPERTY_TERM, PROPERTY_ABBR, DEFINITION_ID, DEFINITION, STATUS_REASON, TXNMY_STATUS, SHORT_SEQ, LONG_SEQ, REQUIRED_FLAG, "
                        + "PDR_FLAG, DATA_TYPE, DRIVEN_BY, LANGUAGE_ID, ATTACH_ID, CREATE_DATE, CREATE_BY, EDIT_DATE, EDIT_BY, APPROVE_DATE, "
                        + "APPROVE_BY, PROCESSED_DATE, PROCESSED_BY, HIGH_LEVEL_ID, STXT_FLAG, LTXT_FLAG, LOCALE, ACTIVE_FLAG, AUDIT_ID, COMMENTS, COPY_FLAG";
            }

            String[] projectColsArray = projectionFields.split(",");
            String query = "";
            String countQuery = "";
            String queryOperator = "";
            String sortedDataField = "";
            String sortOrder = "";
            if (!query.contains("WHERE ")) {
                query = query + " WHERE ";
            }
            if (query.toUpperCase().contains("WHERE")) {
                query = query + " upper(TXNMY_STATUS) =upper('APPROVED')";
                //query = query + " AND upper(CONCEPT_TYPE) =upper('Class')";
                query = query + " AND upper(ORG_ID) =upper('" + taxonomyOperationRequestDto.getOrgnId() + "')";
                query = query + " AND upper(DR_ID) =upper('" + taxonomyOperationRequestDto.getDrId() + "')";
//                        query = query + " AND REQUEST.ORG_ID =CHANGES.ORG_ID";
//                        query = query + " AND REQUEST.DR_ID =CHANGES.DR_ID";
                //query = query + " AND CHANGES.STATE =upper('AV')";

            }
            if (filterData != null && !filterData.isEmpty()) {
                for (int i = 0; i < filterData.size(); i++) {
                    GenerateFilterDTO filterDTO = filterData.get(i);
                    String filterCol = filterDTO.getFilterColumn();
                    String filterCondition = "";
                    String filterValue = filterDTO.getFilterValue();
                    filterCondition = filterDTO.getFilterCondition() == null ? "CONTAINS" : String.valueOf(filterDTO.getFilterCondition());
                    if (query.toUpperCase().contains("WHERE")) {
                        queryOperator = " AND ";
                    } else {
                        queryOperator = " WHERE ";
                    }

                    if (filterCol.toUpperCase().contains("DATE")) {
                        filterValue = filterValue.substring(0, filterValue.indexOf("GMT") - 9).trim();
                        if (filterCondition.equalsIgnoreCase("LESS_THAN_OR_EQUAL")) {
                            filterCol = "TO_DATE(TO_CHAR(" + filterCol + ",'DY MON DD YYYY'), 'DY MON DD YYYY')";
                            filterValue = "TO_DATE('" + filterValue + "','DY MON DD YYYY')";
                            query += queryOperator + " " + filterCol + " <= " + filterValue + "";
//                               query += queryOperator + "   " + filterCol + " "
//                                          + " <= TO_DATE('" + filterValue + "','dd-MM-yyyy')";
                        } else if (filterCondition.equalsIgnoreCase("GREATER_THAN_OR_EQUAL")) {
                            filterCol = "TO_DATE(TO_CHAR(" + filterCol + ",'DY MON DD YYYY'), 'DY MON DD YYYY')";
                            filterValue = "TO_DATE('" + filterValue + "','DY MON DD YYYY')";
                            query += queryOperator + " " + filterCol + " >= " + filterValue + "";
//                                query += queryOperator + "   " + filterCol + " "
//                                         + " >= TO_DATE('" + filterValue + "','dd-MM-yyyy')";
                        }
                    } else {

                        // filterValue = filterValue.toUpperCase();
                        filterValue = filterValue;
                        switch (filterCondition) {
                            case "NOT_EMPTY":
                                query += queryOperator + filterCol + " <> ''";
                                break;
                            case "NOT_NULL":
                                query += queryOperator + filterCol + " NOT NULL";
                                break;
                            case "EMPTY":
                                query += queryOperator + filterCol + " = ''";
                                break;
                            case "NULL":
                                query += queryOperator + " " + filterCol + " IS NULL";
                                break;
                            case "CONTAINS_CASE_SENSITIVE":
                                query += queryOperator + "   " + filterCol + " LIKE '%" + filterValue + "%'";
                                break;
                            case "CONTAINS":
                                filterValue = filterValue.replaceAll("//s", "%") + "%";
                                query += queryOperator + " " + "upper(" + filterCol + ")" + " LIKE upper('%" + filterValue + "%')";
                                break;
                            case "DOES_NOT_CONTAIN_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " NOT LIKE '%" + filterValue + "%'";
                                break;
                            case "DOES_NOT_CONTAIN":
                                query += queryOperator + " " + filterCol + " NOT LIKE '%" + filterValue + "%'";
                                break;
                            case "EQUAL_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " = '" + filterValue + "'";
                                break;
                            case "EQUAL":
                                query += queryOperator + filterCol + " = '" + filterValue + "'";
                                break;
                            case "NOT_EQUAL_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " <> '" + filterValue + "'";
                                break;
                            case "NOT_EQUAL":
                                query += queryOperator + " " + filterCol + " <> '" + filterValue + "'";
                                break;
                            case "GREATER_THAN":
                                query += queryOperator + " " + filterCol + " > '" + filterValue + "'";
                                break;
                            case "LESS_THAN":
                                query += queryOperator + " " + filterCol + " < '" + filterValue + "'";
                                break;
                            case "GREATER_THAN_OR_EQUAL":
                                query += queryOperator + " " + filterCol + " >= '" + filterValue + "'";
                                break;
                            case "LESS_THAN_OR_EQUAL":
                                query += queryOperator + " " + filterCol + " <= '" + filterValue + "'";
                                break;
                            case "STARTS_WITH_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " LIKE '" + filterValue + "%'";
                                break;
                            case "STARTS_WITH":
                                query += queryOperator + " " + filterCol + " LIKE '" + filterValue + "%'";
                                break;
                            case "ENDS_WITH_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " LIKE '%" + filterValue + "'";
                                break;
                            case "ENDS_WITH":
                                query += queryOperator + " " + filterCol + " LIKE '%" + filterValue + "'";
                                break;
                        }
                    }

                }
            }

            if (!String.valueOf(taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField()).equalsIgnoreCase("")
                    && !String.valueOf(taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField()).equalsIgnoreCase("null")) {
                sortedDataField = (String) taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField();
                //query = query + " order by id." + sortField;
                sortOrder = (String) taxonomyOperationRequestDto.getGeneratePageBean().getSortOrder();
                if (sortedDataField != null && sortOrder != null && (sortOrder.equals("asc") || sortOrder.equals("desc"))) {
                    query = query + " order by " + sortedDataField + " " + sortOrder;
                }
            }

            countQuery = " select count(*) FROM MDRM_DR_REQUEST " + query;

            query = "select ROWNUM as RNUM," + projectionFields + " FROM MDRM_DR_REQUEST " + query;

            query = "select * from ( " + query + ") where RNUM between " + (recordStartIndex + 1) + " AND " + recordEndIndex;

            System.out.println("query:::::*****" + query);

            System.out.println("countQuery:::::*****" + countQuery);
            appList = termRepository.getDrTerms(countQuery, query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return appList;
    }

    @Override
    public List<GenerateTermDrDTO> generateConceptDrClassApp(TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
           List appList = new ArrayList();
        try {

            String pageNum = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getPageNum())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("1");

            String pageSize = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getPageSize())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("10");

            String recordStartIndexStr = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getRecordStartIndex())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("0");

            String recordEndIndexStr = Optional.ofNullable(taxonomyOperationRequestDto.getGeneratePageBean().getRecordEndIndex())
                    .filter(p -> !p.equalsIgnoreCase("?") && !p.isEmpty())
                    .orElse("10");

            Integer pageNumber = Integer.parseInt(pageNum);
            Integer pageSizeInt = Integer.parseInt(pageSize);
            Integer recordStartIndex = Integer.parseInt(recordStartIndexStr);
            Integer recordEndIndex = Integer.parseInt(recordEndIndexStr);

            String projectionFields = Optional.ofNullable(taxonomyOperationRequestDto.getProjectionFields())
                    .orElse("");

            List<GenerateFilterDTO> filterData = taxonomyOperationRequestDto.getGeneratePageBean().getFilterData();

            System.out.println("projectFields::" + String.valueOf(projectionFields));

            if (projectionFields.trim().equalsIgnoreCase("")) {
                projectionFields = "REQUEST.ORG_ID, REQUEST.DR_ID, SEQUENCE_EXT_NO, CONCEPT_TYPE, CLASS_CONCEPT_ID, CLASS_TERM, CLASS_TERM_ID,CLASS_ABBR, DOMAIN, PROPERTY_CONCEPT_ID, "
                        + "PROPERTY_TERM,PROPERTY_TERM_ID, PROPERTY_ABBR, DEFINITION_ID, DEFINITION, STATUS_REASON, TXNMY_STATUS, SHORT_SEQ, LONG_SEQ, REQUIRED_FLAG, "
                        + "PDR_FLAG, DATA_TYPE, DRIVEN_BY, LANGUAGE_ID, ATTACH_ID, CREATE_DATE, CREATE_BY, EDIT_DATE, EDIT_BY, APPROVE_DATE, "
                        + "APPROVE_BY, PROCESSED_DATE, PROCESSED_BY, HIGH_LEVEL_ID, STXT_FLAG, LTXT_FLAG, LOCALE, ACTIVE_FLAG, AUDIT_ID, COMMENTS";
            }

            String[] projectColsArray = projectionFields.split(",");
            String query = "";
            String countQuery = "";
            String queryOperator = "";
            String sortedDataField = "";
            String sortOrder = "";
            if (!query.contains("WHERE ")) {
                query = query + " WHERE ";
            }
            if (query.toUpperCase().contains("WHERE")) {
                query = query + " upper(TXNMY_STATUS) =upper('APPROVED')";
                query = query + " AND upper(CONCEPT_TYPE) =upper('Class')";
                query = query + " AND upper(REQUEST.ORG_ID) =upper('" + taxonomyOperationRequestDto.getOrgnId() + "')";
                query = query + " AND REQUEST.ORG_ID =CHANGES.ORG_ID";
                query = query + " AND REQUEST.DR_ID =CHANGES.DR_ID";
                query = query + " AND CHANGES.STATE =upper('AV')";

            }
            if (filterData != null && !filterData.isEmpty()) {
                for (int i = 0; i < filterData.size(); i++) {
                    GenerateFilterDTO filterDTO = filterData.get(i);
                    String filterCol = filterDTO.getFilterColumn();
                    String filterCondition = "";
                    String filterValue = filterDTO.getFilterValue();
                    filterCondition = filterDTO.getFilterCondition() == null ? "CONTAINS" : String.valueOf(filterDTO.getFilterCondition());
                    if (query.toUpperCase().contains("WHERE")) {
                        queryOperator = " AND ";
                    } else {
                        queryOperator = " WHERE ";
                    }

                    if (filterCol.toUpperCase().contains("DATE")) {
                        filterValue = filterValue.substring(0, filterValue.indexOf("GMT") - 9).trim();
                        if (filterCondition.equalsIgnoreCase("LESS_THAN_OR_EQUAL")) {
                            filterCol = "TO_DATE(TO_CHAR(" + filterCol + ",'DY MON DD YYYY'), 'DY MON DD YYYY')";
                            filterValue = "TO_DATE('" + filterValue + "','DY MON DD YYYY')";
                            query += queryOperator + " " + filterCol + " <= " + filterValue + "";
//                               query += queryOperator + "   " + filterCol + " "
//                                          + " <= TO_DATE('" + filterValue + "','dd-MM-yyyy')";
                        } else if (filterCondition.equalsIgnoreCase("GREATER_THAN_OR_EQUAL")) {
                            filterCol = "TO_DATE(TO_CHAR(" + filterCol + ",'DY MON DD YYYY'), 'DY MON DD YYYY')";
                            filterValue = "TO_DATE('" + filterValue + "','DY MON DD YYYY')";
                            query += queryOperator + " " + filterCol + " >= " + filterValue + "";
//                                query += queryOperator + "   " + filterCol + " "
//                                         + " >= TO_DATE('" + filterValue + "','dd-MM-yyyy')";
                        }
                    } else {

                        // filterValue = filterValue.toUpperCase();
                        filterValue = filterValue;
                        switch (filterCondition) {
                            case "NOT_EMPTY":
                                query += queryOperator + filterCol + " <> ''";
                                break;
                            case "NOT_NULL":
                                query += queryOperator + filterCol + " NOT NULL";
                                break;
                            case "EMPTY":
                                query += queryOperator + filterCol + " = ''";
                                break;
                            case "NULL":
                                query += queryOperator + " " + filterCol + " IS NULL";
                                break;
                            case "CONTAINS_CASE_SENSITIVE":
                                query += queryOperator + "   " + filterCol + " LIKE '%" + filterValue + "%'";
                                break;
                            case "CONTAINS":
                                filterValue = filterValue.replaceAll("//s", "%") + "%";
                                query += queryOperator + " " + "upper(" + filterCol + ")" + " LIKE upper('%" + filterValue + "%')";
                                break;
                            case "DOES_NOT_CONTAIN_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " NOT LIKE '%" + filterValue + "%'";
                                break;
                            case "DOES_NOT_CONTAIN":
                                query += queryOperator + " " + filterCol + " NOT LIKE '%" + filterValue + "%'";
                                break;
                            case "EQUAL_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " = '" + filterValue + "'";
                                break;
                            case "EQUAL":
                                query += queryOperator + filterCol + " = '" + filterValue + "'";
                                break;
                            case "NOT_EQUAL_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " <> '" + filterValue + "'";
                                break;
                            case "NOT_EQUAL":
                                query += queryOperator + " " + filterCol + " <> '" + filterValue + "'";
                                break;
                            case "GREATER_THAN":
                                query += queryOperator + " " + filterCol + " > '" + filterValue + "'";
                                break;
                            case "LESS_THAN":
                                query += queryOperator + " " + filterCol + " < '" + filterValue + "'";
                                break;
                            case "GREATER_THAN_OR_EQUAL":
                                query += queryOperator + " " + filterCol + " >= '" + filterValue + "'";
                                break;
                            case "LESS_THAN_OR_EQUAL":
                                query += queryOperator + " " + filterCol + " <= '" + filterValue + "'";
                                break;
                            case "STARTS_WITH_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " LIKE '" + filterValue + "%'";
                                break;
                            case "STARTS_WITH":
                                query += queryOperator + " " + filterCol + " LIKE '" + filterValue + "%'";
                                break;
                            case "ENDS_WITH_CASE_SENSITIVE":
                                query += queryOperator + "  " + filterCol + " LIKE '%" + filterValue + "'";
                                break;
                            case "ENDS_WITH":
                                query += queryOperator + " " + filterCol + " LIKE '%" + filterValue + "'";
                                break;
                        }

                        //query = query + " and upper(" + searchField + ") like '" + searchValue.toUpperCase() + "'";
                    }

                }
            }

            if (!String.valueOf(taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField()).equalsIgnoreCase("")
                    && !String.valueOf(taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField()).equalsIgnoreCase("null")) {
                sortedDataField = (String) taxonomyOperationRequestDto.getGeneratePageBean().getSortedDataField();
                //query = query + " order by id." + sortField;
                sortOrder = (String) taxonomyOperationRequestDto.getGeneratePageBean().getSortOrder();
                if (sortedDataField != null && sortOrder != null && (sortOrder.equals("asc") || sortOrder.equals("desc"))) {
                    query = query + " order by " + sortedDataField + " " + sortOrder;
                }
            }

            countQuery = " select count(*) FROM MDRM_DR_REQUEST REQUEST, DT_DR_CHANGES CHANGES" + query;

            query = "select ROWNUM as RNUM," + projectionFields + " FROM MDRM_DR_REQUEST REQUEST, DT_DR_CHANGES CHANGES " + query;

            query = "select * from ( " + query + ") where RNUM between " + (recordStartIndex + 1) + " AND " + recordEndIndex;

            System.out.println("query:::::*****" + query);

            System.out.println("countQuery:::::*****" + countQuery);
            appList = termRepository.getDrTerms(countQuery, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appList;
    }

    @Transactional
    @Override
    public List<GenerateTermDrDTO> getNewDridInsertion(List<GenerateTermDrDTO> resultList) {
        List<GenerateTermDrDTO> regList = new ArrayList<>();

        if (resultList != null && !resultList.isEmpty()) {
            try {
                for (GenerateTermDrDTO termData : resultList) {
                    System.out.println("Inserting: " + termData);

                    // Prepare the native SQL insert query
                    String queryString = "INSERT INTO MDRM_DR_REQUEST (ORG_ID, DR_ID, SEQUENCE_EXT_NO, CONCEPT_TYPE, CLASS_CONCEPT_ID, CLASS_TERM, CLASS_ABBR, DOMAIN, " +
                            "PROPERTY_CONCEPT_ID, PROPERTY_TERM, PROPERTY_ABBR, DEFINITION, STATUS_REASON, TXNMY_STATUS, SHORT_SEQ, LONG_SEQ, REQUIRED_FLAG, " +
                            "DATA_TYPE, LANGUAGE_ID, CREATE_DATE, CREATE_BY, EDIT_DATE, EDIT_BY, APPROVE_DATE, APPROVE_BY, PROCESSED_DATE, PROCESSED_BY, " +
                            "LOCALE, ACTIVE_FLAG, COMMENTS, COPY_FLAG, APPROVE_FLAG) " +
                            "VALUES (:orgId, :drId, :sequenceExtNo, :conceptType, :classConceptId, :classTerm, :classAbbr, :domain, " +
                            ":propertyConceptId, :propertyTerm, :propertyAbbr, :definition, :statusReason, :txnmyStatus, :shortSeq, :longSeq, " +
                            ":requiredFlag, :dataType, :languageId, :createDate, :createBy, :editDate, :editBy, :approveDate, :approveBy, " +
                            ":processedDate, :processedBy, :locale, :activeFlag, :comments, :copyFlag, :approveFlag)";

                    Query query = entityManager.createNativeQuery(queryString);

                    // Set parameters
                    query.setParameter("orgId", termData.getOrgId());
                    query.setParameter("drId", termData.getDrId());
                    query.setParameter("sequenceExtNo", termData.getSequenceExtNo()); 
                    query.setParameter("conceptType", termData.getConceptType()); 
                    query.setParameter("classConceptId", termData.getClassConceptId()); 
                    query.setParameter("classTerm", termData.getClassTerm()); 
                    query.setParameter("classAbbr", termData.getClassAbbr()); 
                    query.setParameter("domain", termData.getDomain());
                    query.setParameter("propertyConceptId", termData.getPropertyConceptId()); 
                    query.setParameter("propertyTerm", termData.getPropertyTerm());
                    query.setParameter("propertyAbbr", termData.getPropertyAbbr()); 
                    query.setParameter("definition", termData.getDefinition()); 
                    query.setParameter("statusReason", termData.getStatusReason()); 
                    query.setParameter("txnmyStatus", termData.getTxnmyStatus()); 
                    query.setParameter("shortSeq", termData.getShortSeq()); 
                    query.setParameter("longSeq", termData.getLongSeq());
                    query.setParameter("requiredFlag", termData.getRequiredFlag());
                    query.setParameter("dataType", termData.getDataType());
                    query.setParameter("languageId", termData.getLanguageId());
                    query.setParameter("createDate", termData.getCreateDate());
                    query.setParameter("createBy", termData.getCreateBy());
                    query.setParameter("editDate", termData.getEditDate());
                    query.setParameter("editBy", termData.getEditBy());
                    query.setParameter("approveDate", termData.getApproveDate());
                    query.setParameter("approveBy", termData.getApproveBy());
                    query.setParameter("processedDate", termData.getProcessedDate());
                    query.setParameter("processedBy", termData.getProcessedBy());
                    query.setParameter("locale", termData.getLocale());
                    query.setParameter("activeFlag", termData.getActiveFlag());
                    query.setParameter("comments", termData.getComments());
                    query.setParameter("copyFlag", termData.getCopyFlag());
                    query.setParameter("approveFlag", termData.getApproveFlag());


                    int rowCount = query.executeUpdate();

                    if (rowCount > 0) {
                        termData.setMessage("Class and Characteristics Inserted Successfully");
                        termData.setMessageFlag(Boolean.toString(true));
                    } else {
                        termData.setMessage("Class and Characteristics insertion operation failed");
                        termData.setMessageFlag(Boolean.toString(false));
                    }
                    regList.add(termData);
                }
            } catch (Exception e) {
                for (GenerateTermDrDTO termData : resultList) {
                    termData.setMessage("Class/Property Characteristics operation failed");
                    termData.setMessageFlag(Boolean.toString(false));
                    regList.add(termData);
                }
                e.printStackTrace();
            }
        }

        return regList;
    }

    @Override
    public List<String> getIrdi(TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        return conceptRegistrationRequestRepository.findIrdIds(taxonomyOperationRequestDto.getConceptTypeId(), taxonomyOperationRequestDto.getLanguageId(), taxonomyOperationRequestDto.getOrgnId(), taxonomyOperationRequestDto.getTerm(), taxonomyOperationRequestDto.getStatusRc());
    }

    @Override
    public List<String> getDrid(TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        return conceptRegistrationRequestRepository.findDrIds(taxonomyOperationRequestDto.getConceptTypeId(), taxonomyOperationRequestDto.getLanguageId(), taxonomyOperationRequestDto.getOrgnId(), taxonomyOperationRequestDto.getTerm(), taxonomyOperationRequestDto.getStatusRc());
    }

    @Override
    public List<GenerateTermDTO> generateConceptClass(TaxonomyOperationRequestDto taxonomyOperationRequestDto) {
        List<GenerateTermDTO> regList = new ArrayList<>();
        GenerateTermDTO termData = new GenerateTermDTO();
        boolean flag = false;

        StringBuilder message = new StringBuilder();
        if (taxonomyOperationRequestDto.getDomain() == null && "2A00A96C173342ABA2EBD2A697138CC5".equalsIgnoreCase(taxonomyOperationRequestDto.getConceptTypeId())) {
            message.append("Please provide domain");
        }
        if (taxonomyOperationRequestDto.getConceptTypeId() == null) {
            message.append((message.length() > 0 ? ", " : "") + "Please provide conceptTypeId");
        }
        if (taxonomyOperationRequestDto.getLanguageId() == null) {
            message.append((message.length() > 0 ? ", " : "") + "Please provide languageId");
        }
        if (taxonomyOperationRequestDto.getOrgnId() == null) {
            message.append((message.length() > 0 ? ", " : "") + "Please provide orgnId");
        }
        if (taxonomyOperationRequestDto.getTerm() == null) {
            message.append((message.length() > 0 ? ", " : "") + "Please provide term");
        }
        if (taxonomyOperationRequestDto.getLanguageIRD1() == null) {
            message.append((message.length() > 0 ? ", " : "") + "Please provide languageIRD1");
        }

        if (message.length() > 0) {
            termData.setMessage(message.toString());
            termData.setMessageFlag(Boolean.toString(false));
            regList.add(termData);
            return regList;
        }

        String conceptStr = taxonomyOperationRequestDto.getConceptTypeId();
        String orgnStr = taxonomyOperationRequestDto.getOrgnId();
        String languageStr = taxonomyOperationRequestDto.getLanguageId();

        ConceptRegistrationRequest newConcept = new ConceptRegistrationRequest();
        newConcept.setConceptTypeId(conceptStr);
        newConcept.setOrgnId(orgnStr);
        newConcept.setLanguageId(languageStr);
        newConcept.setTerm(taxonomyOperationRequestDto.getTerm());
        newConcept.setDefinition(taxonomyOperationRequestDto.getDefinition());
        newConcept.setAbbreviation(taxonomyOperationRequestDto.getAbbrevation());
        newConcept.setLabel(taxonomyOperationRequestDto.getLabel());
        newConcept.setSysCreatedBy(taxonomyOperationRequestDto.getSysCreatedBy());
        newConcept.setDomain(taxonomyOperationRequestDto.getDomain());
        newConcept.setStatusRc(taxonomyOperationRequestDto.getStatusRc());
        newConcept.setStatusReason(taxonomyOperationRequestDto.getStatusReason());
        newConcept.setSysCreatedOn(LocalDateTime.now());
        newConcept.setLanguageIrd1(taxonomyOperationRequestDto.getLanguageIRD1());

        conceptRegistrationRequestRepository.save(newConcept);

        termData.setMessage("Created Term Successfully");
        termData.setMessageFlag(Boolean.toString(true));
        regList.add(termData);

        return regList;
    }
}

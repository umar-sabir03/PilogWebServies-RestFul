package com.pilog.plontology.service.impl;

import com.google.common.io.Files;
import com.pilog.plontology.model.apexsrs.OAttachment;
import com.pilog.plontology.model.apexsrs.OSRSProperties;
import com.pilog.plontology.model.apexsrs.OSRSRequests;
import com.pilog.plontology.model.apexsrs.OrgStructure;
import com.pilog.plontology.payloads.*;
import com.pilog.plontology.repository.apexsrs.OAttachmentRepository;
import com.pilog.plontology.repository.apexsrs.OSRSRequestRepository;
import com.pilog.plontology.repository.apexsrs.OrgStructureRepository;
import com.pilog.plontology.repository.apexsrs.SRSRepository;
import com.pilog.plontology.service.IV10SRSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class V10SRSServiceImpl implements IV10SRSService {
//    @PersistenceContext

    @Autowired
    private SRSRepository srsRepo;
    @Autowired
    private OSRSProperties osrsProperties;
    @Autowired
    private OrgStructureRepository orgStructureRepository;

    @Autowired
    private OSRSRequestRepository osrsRequestRepository;

    @Autowired
    private OAttachmentRepository attachmentRepository;

    @Override
    public List<SRSResponseDTO> getSrsData(V10SRSRequestBean v10SRSRequestBean) {
        List<SRSResponseDTO> resultList = new ArrayList();
        v10SRSRequestBean.pagenum = v10SRSRequestBean.pagenum == null ? "" : v10SRSRequestBean.pagenum;
        v10SRSRequestBean.pagenum = v10SRSRequestBean.pagenum.equalsIgnoreCase("?") ? "" : v10SRSRequestBean.pagenum;
        v10SRSRequestBean.pagenum = v10SRSRequestBean.pagenum.equalsIgnoreCase("") ? "1" : v10SRSRequestBean.pagenum;
        v10SRSRequestBean.pageSize = v10SRSRequestBean.pageSize == null ? "" : v10SRSRequestBean.pageSize;
        v10SRSRequestBean.pageSize = v10SRSRequestBean.pageSize.equalsIgnoreCase("?") ? "" : v10SRSRequestBean.pageSize;
        v10SRSRequestBean.pageSize = v10SRSRequestBean.pageSize.equalsIgnoreCase("") ? "10" : v10SRSRequestBean.pageSize;
        v10SRSRequestBean.recordstartindex = v10SRSRequestBean.recordstartindex == null ? "" : v10SRSRequestBean.recordstartindex;
        v10SRSRequestBean.recordstartindex = v10SRSRequestBean.recordstartindex.equalsIgnoreCase("?") ? "" : v10SRSRequestBean.recordstartindex;
        v10SRSRequestBean.recordstartindex = v10SRSRequestBean.recordstartindex.equalsIgnoreCase("") ? "0" : v10SRSRequestBean.recordstartindex;
        v10SRSRequestBean.recordendindex = v10SRSRequestBean.recordendindex == null ? "" : v10SRSRequestBean.recordendindex;
        v10SRSRequestBean.recordendindex = v10SRSRequestBean.recordendindex.equalsIgnoreCase("?") ? "" : v10SRSRequestBean.recordendindex;
        v10SRSRequestBean.recordendindex = v10SRSRequestBean.recordendindex.equalsIgnoreCase("") ? "10" : v10SRSRequestBean.recordendindex;
        Integer pageNumber = Integer.parseInt(v10SRSRequestBean.pagenum);
        Integer pageSize = Integer.parseInt(v10SRSRequestBean.pageSize);
        Integer recordStartIndex = Integer.parseInt(v10SRSRequestBean.recordstartindex);
        Integer recordEndIndex = Integer.parseInt(v10SRSRequestBean.recordendindex);
        String projectionFields = v10SRSRequestBean.projectionFields;
        String jsParamArrayString = v10SRSRequestBean.searchData;
        List<SRSGridFilterDTO> filterData = v10SRSRequestBean.filterData;

        try {
            projectionFields = projectionFields == null ? "" : projectionFields;
            if (projectionFields.trim().equalsIgnoreCase("")) {
                projectionFields = "SRS_ID, REQUEST, " +
                        "REQUEST_TYPE, ISSUE, " +
                        "PRIORITY, STATUS, " +
                        "ORGANISATION, APPLICATION, " +
                        "REQUESTED_BY, ASSIGNED_BY, " +
                        "ASSIGNED_TO,REQUESTED_DATE, " +
                        "J_CREATE_DATE, " +
                        "TECH_SUP_DESC, " +
                        "CLIENT_USER_NAME, " +
                        "CLOSED_BY";
            }
            String query = "";
            String queryOperator = "";
            String sortedDataField = "";
            String sortOrder = "";

            if (filterData != null && !filterData.isEmpty()) {
                StringBuilder queryBuilder = new StringBuilder(query);
                for (int i = 0; i < filterData.size(); i++) {
                    SRSGridFilterDTO filterDTO = (SRSGridFilterDTO) filterData.get(i);
                    if (filterDTO.getFilterColumn() != null && filterDTO.getFilterValue() != null && filterDTO.getFilterCondition() != null) {
                        String filterCol = filterDTO.getFilterColumn();
                        String filterValue = filterDTO.getFilterValue();
                        String filterCondition = filterDTO.getFilterCondition() == null ? "CONTAINS" : filterDTO.getFilterCondition().toUpperCase();
                        queryOperator = query.toUpperCase().contains("WHERE") ? " AND " : " WHERE ";

                        if (filterCol.toUpperCase().contains("DATE")) {
                            filterValue = filterValue.substring(0, filterValue.indexOf("GMT") - 9).trim();
                            filterCol = "TO_DATE(TO_CHAR(" + filterCol + ",'DY MON DD YYYY'), 'DY MON DD YYYY')";
                            filterValue = "TO_DATE('" + filterValue + "','DY MON DD YYYY')";
                            switch (filterCondition) {
                                case "LESS_THAN_OR_EQUAL":
                                    queryBuilder.append(queryOperator).append(" ").append(filterCol).append(" <= ").append(filterValue);
                                    break;
                                case "GREATER_THAN_OR_EQUAL":
                                    queryBuilder.append(queryOperator).append(" ").append(filterCol).append(" >= ").append(filterValue);
                                    break;
                            }
                        } else {
                            switch (filterCondition) {
                                case "NOT_EMPTY":
                                    queryBuilder.append(queryOperator).append(filterCol).append(" <> ''");
                                    break;
                                case "NOT_NULL":
                                    queryBuilder.append(queryOperator).append(filterCol).append(" NOT NULL");
                                    break;
                                case "EMPTY":
                                    queryBuilder.append(queryOperator).append(filterCol).append(" = ''");
                                    break;
                                case "NULL":
                                    queryBuilder.append(queryOperator).append(" ").append(filterCol).append(" IS NULL");
                                    break;
                                case "CONTAINS_CASE_SENSITIVE":
                                    queryBuilder.append(queryOperator).append(" ").append(filterCol).append(" LIKE '%").append(filterValue).append("%'");
                                    break;
                                case "CONTAINS":
                                    filterValue = filterValue.replaceAll("\\s", "%") + "%";
                                    queryBuilder.append(queryOperator).append(" upper(").append(filterCol).append(") LIKE upper('%").append(filterValue).append("%')");
                                    break;
                                case "DOES_NOT_CONTAIN_CASE_SENSITIVE":
                                case "DOES_NOT_CONTAIN":
                                    queryBuilder.append(queryOperator).append(" ").append(filterCol).append(" NOT LIKE '%").append(filterValue).append("%'");
                                    break;
                                case "EQUAL_CASE_SENSITIVE":
                                case "EQUAL":
                                    queryBuilder.append(queryOperator).append(" ").append(filterCol).append(" = '").append(filterValue).append("'");
                                    break;
                                case "NOT_EQUAL_CASE_SENSITIVE":
                                case "NOT_EQUAL":
                                    queryBuilder.append(queryOperator).append(" ").append(filterCol).append(" <> '").append(filterValue).append("'");
                                    break;
                                case "GREATER_THAN":
                                    queryBuilder.append(queryOperator).append(" ").append(filterCol).append(" > '").append(filterValue).append("'");
                                    break;
                                case "LESS_THAN":
                                    queryBuilder.append(queryOperator).append(" ").append(filterCol).append(" < '").append(filterValue).append("'");
                                    break;
                                case "GREATER_THAN_OR_EQUAL":
                                    queryBuilder.append(queryOperator).append(" ").append(filterCol).append(" >= '").append(filterValue).append("'");
                                    break;
                                case "LESS_THAN_OR_EQUAL":
                                    queryBuilder.append(queryOperator).append(" ").append(filterCol).append(" <= '").append(filterValue).append("'");
                                    break;
                                case "STARTS_WITH_CASE_SENSITIVE":
                                case "STARTS_WITH":
                                    queryBuilder.append(queryOperator).append(" ").append(filterCol).append(" LIKE '").append(filterValue).append("%'");
                                    break;
                                case "ENDS_WITH_CASE_SENSITIVE":
                                case "ENDS_WITH":
                                    queryBuilder.append(queryOperator).append(" ").append(filterCol).append(" LIKE '%").append(filterValue).append("'");
                                    break;
                            }
                        }
                    }
                }
                query = queryBuilder.toString();
            }


            if (!String.valueOf(v10SRSRequestBean.getSortedDataField()).equalsIgnoreCase("") && !String.valueOf(v10SRSRequestBean.getSortedDataField()).equalsIgnoreCase("null")) {
                sortedDataField = v10SRSRequestBean.getSortedDataField();
                sortOrder = v10SRSRequestBean.getSortOrder();

                if (sortedDataField != null && !sortedDataField.isEmpty() && !"null".equalsIgnoreCase(sortedDataField)
                        && sortOrder != null && ("asc".equalsIgnoreCase(sortOrder) || "desc".equalsIgnoreCase(sortOrder))) {
                    StringBuilder queryBuilder = new StringBuilder(query);
                    queryBuilder.append(" ORDER BY ").append(sortedDataField).append(" ").append(sortOrder);
                    query = queryBuilder.toString();
                }
            }

//            recordStartIndex = (pageNumber - 1) * pageSize;
//            recordEndIndex = pageNumber * pageSize;

            StringBuilder countQueryBuilder = new StringBuilder("SELECT COUNT(*) FROM SRS_STATUS_REPORT1").append(query);
            String countQuery = countQueryBuilder.toString();

            StringBuilder queryBuilder = new StringBuilder("SELECT ROWNUM AS RNUM, ").append(projectionFields).append(" FROM SRS_STATUS_REPORT1").append(query);
            query = queryBuilder.toString();

            StringBuilder finalQueryBuilder = new StringBuilder("SELECT * FROM (").append(query).append(") WHERE RNUM BETWEEN ")
                    .append(recordStartIndex + 1).append(" AND ").append(recordEndIndex);
            ;
            String finalQuery = finalQueryBuilder.toString();
            System.out.println("query:::::*****" + finalQuery);
            System.out.println("countQuery:::::*****" + countQuery);

//            BigDecimal count = (BigDecimal) entityManager.createNativeQuery(countQuery).getSingleResult();
//            @SuppressWarnings("unchecked")
//            List<Tuple> resultListObj = entityManager.createNativeQuery(finalQuery, Tuple.class).getResultList();
//            List<Map<String, Object>> result = convertTuplesToMap(resultListObj);
            BigDecimal count = srsRepo.getCount(countQuery);
            List<Map<String, Object>> finalResults = srsRepo.getFinalResults(finalQuery);
            for (Map<String, Object> mapObj : finalResults) {
                SRSResponseDTO data = new SRSResponseDTO();
                data.setSrsId(getStringValue(mapObj.get("SRS_ID")));
                data.setSrsDesc(getStringValue(mapObj.get("REQUEST")));
                data.setRequestType(getStringValue(mapObj.get("REQUEST_TYPE")));
                data.setIssue(getStringValue(mapObj.get("ISSUE")));
                data.setPriority(getStringValue(mapObj.get("PRIORITY")));
                data.setStatus(getStringValue(mapObj.get("STATUS")));
                data.setOrganisation(getStringValue(mapObj.get("ORGANISATION")));
                data.setApplication(getStringValue(mapObj.get("APPLICATION")));
                data.setRequestedBy(getStringValue(mapObj.get("REQUESTED_BY")));
                data.setAssignedBy(getStringValue(mapObj.get("ASSIGNED_BY")));
                data.setAssignedTo(getStringValue(mapObj.get("ASSIGNED_TO")));
                data.setRequestedDate((Timestamp) mapObj.get("REQUESTED_DATE"));
                data.setJCreateDate((Date) mapObj.get("J_CREATE_DATE"));
                data.setTechSupDesc(getStringValue(mapObj.get("TECH_SUP_DESC")));
               data.setClosedBy(getStringValue(mapObj.get("CLOSED_BY")));
               data.setClientUserName(getStringValue(mapObj.get("CLIENT_USER_NAME")));
                data.setTotalCount(count);
                resultList.add(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }

    private String getStringValue(Object value) {
        return (value != null) ? value.toString() : "";
    }

    @Override
    @Transactional
    public String createSRSForV10(V10SRSBean v10SRSBean) {
        OSRSRequests osrsRequest = new OSRSRequests();
        String result = "";
        String srsId = srsRepo.getSRSId();
        osrsRequest.setSrsId(srsId);
        osrsRequest.setSrsDesc(v10SRSBean.getSrsDescription());
        osrsRequest.setRequestTypeId(osrsProperties.getTypeid());
        osrsRequest.setUserRequestId(osrsProperties.getUserrequestid());
        osrsRequest.setRequestedDate(new Timestamp(System.currentTimeMillis()));
        osrsRequest.setPriority(v10SRSBean.getCriticallity());
        osrsRequest.setStatusId(osrsProperties.getStatusid());
        osrsRequest.setJCreateDate(new Date());
        osrsRequest.setJCreateUser(osrsRequest.getJCreateUser());
        osrsRequest.setJEditDate(new Date());
        osrsRequest.setJEditUser(osrsProperties.getJedituser());
        osrsRequest.setOrgId(getORGID(v10SRSBean.getSrsorgn()));
        osrsRequest.setIssueId(osrsProperties.getIssueid());
        osrsRequest.setAppId(osrsProperties.getAppid());
        osrsRequest.setAppSupportId(osrsProperties.getAppsupportid());
        osrsRequest.setAssignedBy(osrsProperties.getAssignedby());
        osrsRequest.setAltAssignedBy(osrsProperties.getAltassignedby());
        osrsRequest.setProcessFlag(osrsProperties.getProcessflag());
        osrsRequest.setClientUserName(v10SRSBean.getV10user());
        osrsRequest.setClientEmailId(v10SRSBean.getV10emailid());
        osrsRequestRepository.save(osrsRequest);

        result = srsId;
        if (v10SRSBean != null && v10SRSBean.getSrsFileRequestData() != null && !v10SRSBean.getSrsFileRequestData().isEmpty()) {
            this.saveFiles(v10SRSBean, "VISIONUSER", srsId);
        }
        return result;
    }

    private String getORGID(String srsorgn) {
        OrgStructure orgnStructureObj = orgStructureRepository.findByNameIgnoreCase(srsorgn);
        if (orgnStructureObj == null) {
            throw new IllegalArgumentException(" srsorgn Not found");
        }
        return  orgnStructureObj.getOrgId();
    }

    public void saveFiles(V10SRSBean v10SRSBean, String userName, String srsId) {
        List<SRSFileRequestDTO> fileListRequests = v10SRSBean.getSrsFileRequestData();
        if (fileListRequests != null && !fileListRequests.isEmpty()) {
            for (SRSFileRequestDTO fileRequests : fileListRequests) {
                File file = new File("C:/Test/Server/Download" + File.separator + fileRequests.getFileName());
                try (FileInputStream fis = new FileInputStream(file)) {
                    byte[] blobData = new byte[fis.available()];
                    OAttachment attachment = new OAttachment();
                    attachment.setSrsId(srsId);
                    attachment.setFileName(fileRequests.getFileName());
                    attachment.setDocType(getExtensionByGuava(fileRequests.getFileName()));
                    attachment.setBlobData(blobData);
                    attachment.setUserName(userName);
                    attachmentRepository.save(attachment);
                } catch (IOException e) {
                    throw new RuntimeException("Error reading file: " + file.getName(), e);
                }
            }
        }
    }

    private String getExtensionByGuava(String filename) {
        return Files.getFileExtension(filename);
    }


}

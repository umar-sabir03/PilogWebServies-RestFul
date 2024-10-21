package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.RepResponse;
import com.pilog.plontology.payloads.RepResponseDTO;
import com.pilog.plontology.payloads.RepositoryRequestSAP;
import com.pilog.plontology.payloads.RepositoryRequestSAPDTO;
import com.pilog.plontology.repository.pprm.PRSapRepository;
import com.pilog.plontology.service.IRepSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepSearchServiceImpl implements IRepSearchService {
    @Autowired
    private PRSapRepository prSapRepository;

    @Override
    public RepResponseDTO searchRepositoryXml(RepositoryRequestSAPDTO request){
        List<RepResponse> repResponses = new ArrayList<>();

        System.out.println("repSearch::::::::");
        List<RepositoryRequestSAP> repositoryRequestSAPList = null;
        RepResponseDTO repResponseDTO = new RepResponseDTO();
        try {
            if (request != null) {
                repositoryRequestSAPList = request.getRepRequestList();
            }
            request.recordstartindex = request.recordstartindex == null ? "" : request.recordstartindex;
            request.recordstartindex = request.recordstartindex.equalsIgnoreCase("?") ? "" : request.recordstartindex;
            request.recordstartindex = request.recordstartindex.equalsIgnoreCase("") ? "0" : request.recordstartindex;

            request.recordendindex = request.recordendindex == null ? "" : request.recordendindex;
            request.recordendindex = request.recordendindex.equalsIgnoreCase("?") ? "" : request.recordendindex;
            request.recordendindex = request.recordendindex.equalsIgnoreCase("") ? "10" : request.recordendindex;

            request.locale = request.locale == null ? "" : request.locale;
            request.locale = request.locale.equalsIgnoreCase("?") ? "" : request.locale;
            request.locale = request.locale.equalsIgnoreCase("") ? "en_US" : request.locale;

            String trustLevel = request.trustLevel;
            String qualityLevel = request.qualityLevel;

            Integer recordStartIndex = Integer.parseInt(request.recordstartindex);
            Integer recordEndIndex = Integer.parseInt(request.recordendindex);
            String projectionFields = "";

            String query = "", symbol = "", datatype = "", opValue = "", operatorName = "", colName = "";
            String locale = request.locale;
            locale = locale == null ? "" : locale;

            String countQuery = "";
            try {

                projectionFields = "RECORD_NO, ERPSFD, PURCHASE, CLASS, LOCALE, REGION, UNSPSC_CODE, UNSPSC_DESC, CONCEPT_ID, ORGN_ID, QUALITY_LEVEL, TRUST_LEVEL, ACTIVE_STATUS, CREATE_BY, REFERENCE_NO, REFERENCE_TYPE, VENDOR_NAME, STRIP_REFERENCE_NO, RESEARCH_URL, UOM, MATL_TYPE";
                System.out.println("repositoryRequestSAPList::::::::" + repositoryRequestSAPList.size());
                for (int i = 0; i < repositoryRequestSAPList.size(); i++) {
                    RepositoryRequestSAP repositoryRequestSAP = repositoryRequestSAPList.get(i);
                    colName = repositoryRequestSAP.getColumn();
                    datatype = repositoryRequestSAP.getDatatype();
                    operatorName = repositoryRequestSAP.getOperator();
                    operatorName = operatorName.trim();
                    opValue = repositoryRequestSAP.getValue() == null ? "" : repositoryRequestSAP.getValue();

                    if (!opValue.equalsIgnoreCase("")) {
                        if (datatype.equalsIgnoreCase("boolean") && opValue.equalsIgnoreCase("N")) {
                            continue;
                        }
                        opValue = opValue.toUpperCase().trim();
                        if (operatorName.equalsIgnoreCase("EQUALS")) {
                            if (datatype.equalsIgnoreCase("date")) {
                                colName = " TO_CHAR(" + colName + ",'dd-MM-yyyy')";
                            } else {
                            }
                            operatorName = "=";
                            opValue = "'" + opValue + "'";

                        } else if (operatorName.equalsIgnoreCase("NOT EQUALS")) {
                            if (datatype.equalsIgnoreCase("date")) {
                                colName = " TO_CHAR(" + colName + ",'dd-MM-yyyy')";
                            }
                            operatorName = "<>";
                            opValue = "'" + opValue + "'";
                        } else if (operatorName.equalsIgnoreCase("BEGINING WITH")) {
                            opValue = "'" + opValue + "%'";
                            operatorName = " LIKE ";
                        } else if (operatorName.equalsIgnoreCase("ENDING WITH")) {
                            opValue = "'%" + opValue + "'";
                            operatorName = " LIKE ";
                        } else if (operatorName.equalsIgnoreCase("LIKE")) {
                            opValue = "'%" + opValue + "%'";
                            operatorName = " LIKE ";
                        } else if (operatorName.equalsIgnoreCase("NOT LIKE")) {
                            opValue = "'%" + opValue + "%'";
                            operatorName = " NOT LIKE ";
                        } else if (operatorName.equalsIgnoreCase("CONTAINING")) {
                            operatorName = " LIKE ";
                            opValue = "'%" + opValue + "%'";
                        } else if (operatorName.equalsIgnoreCase("IS")) {
                            opValue = " IS NULL";
                            operatorName = "";
                        } else if (operatorName.equalsIgnoreCase("IS NOT")) {
                            opValue = " IS NOT NULL";
                            operatorName = "";
                        } else if (operatorName.equalsIgnoreCase("LESS THAN")) {
                            operatorName = "<";
                            if (datatype.equalsIgnoreCase("date")) {
                                opValue = " TO_DATE('" + opValue + "','dd-MM-yyyy')";
                            } else {
                                opValue = "'" + opValue + "'";
                            }
                        } else if (operatorName.equalsIgnoreCase("GREATER THAN")) {
                            operatorName = " > ";
                            if (datatype.equalsIgnoreCase("date")) {
                                opValue = " TO_DATE('" + opValue + "','dd-MM-yyyy')";
                            } else {
                                opValue = " '" + opValue + "'";
                            }

                        } else if (operatorName.equalsIgnoreCase("IN")) {
                            operatorName = " IN ";
                            if (datatype.equalsIgnoreCase("date")) {
                                opValue = " TO_DATE('" + opValue + "','dd-MM-yyyy')";
                            } else {
                                opValue = opValue.replaceAll(",", "','");
                                System.out.println("in value::::0" + opValue);
                                opValue = " ('" + opValue + "' )";
                            }

                        }
                        if (i == 0) {

                            query = query + " WHERE ";
                        } else {

                            query = query + " AND ";
                        }

                        query += " " + colName + " " + operatorName + " " + opValue;
                    }
                }// for
                try {

                    if (query.toUpperCase().contains("WHERE")) {
                        query = query + " AND LOCALE ='" + locale + "'";
                        if (trustLevel != null && !"".equalsIgnoreCase(trustLevel)) {
                            query = query + " AND TRUST_LEVEL ='" + trustLevel + "'";
                        }
                        if (qualityLevel != null && !"".equalsIgnoreCase(qualityLevel)) {
                            query = query + " AND QUALITY_LEVEL ='" + qualityLevel + "'";
                        }
                    } else {
                        query = query + " WHERE LOCALE ='" + locale + "'";
                        if (trustLevel != null && !"".equalsIgnoreCase(trustLevel)) {
                            query = query + " AND TRUST_LEVEL ='" + trustLevel + "'";
                        }
                        if (qualityLevel != null && !"".equalsIgnoreCase(qualityLevel)) {
                            query = query + " AND QUALITY_LEVEL ='" + qualityLevel + "'";
                        }

                    }

                } catch (Exception e) {
                }
                System.out.println("projection fields::::" + projectionFields);

                countQuery = "select count(*) FROM MV_SMART_SEARCH_TEXT_REF " + query;
                query = "select ROWNUM as RNUM," + projectionFields + " FROM MV_SMART_SEARCH_TEXT_REF " + query;

                query = "select * from ( " + query + ") where RNUM between " + (recordStartIndex + 1) + " AND " + recordEndIndex;

                System.out.println(countQuery);
                Long count = 0L;
                count=prSapRepository.executeDynamicCountQuery(countQuery);

                repResponses = prSapRepository.executeDynamicQuery(query);

                repResponseDTO.setCount(repResponses.size() + "/" + count);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("repResponses.size::::::" + repResponses.size());

        repResponseDTO.setRepResponseList(repResponses);

        return repResponseDTO;
    }
}

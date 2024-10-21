package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.RepositorySearchRequest;
import com.pilog.plontology.payloads.RepositorySearchResponse;
import com.pilog.plontology.repository.pprm.MvSmartSearchTextRefRepository;
import com.pilog.plontology.service.IPilogRepositoryService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class PilogRepositoryServiceImpl implements IPilogRepositoryService {
    @Autowired
    private MvSmartSearchTextRefRepository mvSmartSearchTextRefRepository;

    private static final Logger logger = Logger.getLogger(PilogRepositoryServiceImpl.class.getName());

    public String searchRepository(RepositorySearchRequest request)  {

        RepositorySearchResponse repSearch = new RepositorySearchResponse();
        JSONObject jsResponseObject = new JSONObject();

        try {
            request.projectionFields = request.projectionFields == null ? "" : request.projectionFields;
            request.projectionFields = request.projectionFields.equalsIgnoreCase("?") ? "" : request.projectionFields;

            request.pagenum = request.pagenum == null ? "" : request.pagenum;
            request.pagenum = request.pagenum.equalsIgnoreCase("?") ? "" : request.pagenum;
            request.pagenum = request.pagenum.equalsIgnoreCase("") ? "1" : request.pagenum;

            request.searchData = request.searchData == null ? "" : request.searchData;
            request.searchData = request.searchData.equalsIgnoreCase("?") ? "" : request.searchData;
            request.searchData = request.searchData.equalsIgnoreCase("") ? "[]" : request.searchData;

            request.pageSize = request.pageSize == null ? "" : request.pageSize;
            request.pageSize = request.pageSize.equalsIgnoreCase("?") ? "" : request.pageSize;
            request.pageSize = request.pageSize.equalsIgnoreCase("") ? "10" : request.pageSize;

            request.recordstartindex = request.recordstartindex == null ? "" : request.recordstartindex;
            request.recordstartindex = request.recordstartindex.equalsIgnoreCase("?") ? "" : request.recordstartindex;
            request.recordstartindex = request.recordstartindex.equalsIgnoreCase("") ? "0" : request.recordstartindex;

            request.recordendindex = request.recordendindex == null ? "" : request.recordendindex;
            request.recordendindex = request.recordendindex.equalsIgnoreCase("?") ? "" : request.recordendindex;
            request.recordendindex = request.recordendindex.equalsIgnoreCase("") ? "10" : request.recordendindex;

            request.locale = request.locale == null ? "" : request.locale;
            request.locale = request.locale.equalsIgnoreCase("?") ? "" : request.locale;
            request.locale = request.locale.equalsIgnoreCase("") ? "en_US" : request.locale;
            Integer recordStartIndex = Integer.parseInt(request.recordstartindex);
            Integer recordEndIndex = Integer.parseInt(request.recordendindex);

            String projectionFields = request.projectionFields;
            String jsParamArrayString = request.searchData;
            String andOrOperator = "";
            String typeSelectStr = "";
            String dlovcolname = "";

            JSONArray jsParamsArray = (JSONArray)(new JSONParser()).parse(jsParamArrayString);
            String query = "";
            String symbol = "";
            String datatype = "";
            String opValue = "";
            String operatorName = "";
            String colName = "";

            String locale = request.locale;
            locale = locale == null ? "" : locale;
            JSONArray jsResultsArray = new JSONArray();
            String countQuery = "";

            try {
                String domainType = request.getDomain();
                String tableName = "";
                System.out.println("projectFields::" + projectionFields);
                projectionFields = projectionFields == null ? "" : projectionFields;
                if (projectionFields.trim().equalsIgnoreCase("")) {
                    if (domainType != null && !"".equalsIgnoreCase(domainType) && !"null".equalsIgnoreCase(domainType)
                            && "EBOM".equalsIgnoreCase(domainType)) {
                        projectionFields = "RECORD_NO, CLASS_TERM, EQUIPMENT_NAME, EQUIPMENT_MODEL, VENDOR_NAME, REFERENCE_NO, "
                                + "PURCHASE, UNSPSC, UNSPSC_DESC, ISIC_CODE, ISIC_DESC";
                        tableName = "MV_SMART_SEARCH_TEXT_EBOM";
                    } else if (domainType != null && !"".equalsIgnoreCase(domainType) && !"null".equalsIgnoreCase(domainType)
                            && "VENDOR".equalsIgnoreCase(domainType)) {
                        projectionFields = "CLASS, VENDOR_NAME, VENDOR_TYPE, COUNTRY_KEY, COUNTRY_NAME, CITY, DISTRICT_STATE, "
                                + "PO_BOX, POSTAL_CODE, HEAD_OFFICE_ADRESS, STREET_ADDRESS, TELEPHONE_NUMBER, EMAIL_ADDRESS, "
                                + "WEB_LINK, UNSPSC, UNSPSC_DESCRIPTION, ISIC_CODE, ISIC_DESCRIPTION, SERVICE_PROVIDER, ERPSFD,"
                                + " PURCHASE";
                        tableName = "MV_SMART_SEARCH_TEXT_VENDORS";
                    } else if (domainType != null && !"".equalsIgnoreCase(domainType) && !"null".equalsIgnoreCase(domainType)
                            && "SERVICE".equalsIgnoreCase(domainType)) {
                        projectionFields = "RECORD_NO, CATEGORY, SUB_CATEGORY, CLASS_TERM, SHORT_TEXT, PURCHASE_TEXT,"
                                + " UNSPSC_CODE, UNSPSC_DESC, ISIC_CODE, ISIC_DESC";
                        tableName = "MV_SMART_SEARCH_TEXT_SERV";
                    } else if (domainType != null && !"".equalsIgnoreCase(domainType) && !"null".equalsIgnoreCase(domainType)
                            && "PRODUCT".equalsIgnoreCase(domainType)) {
                        projectionFields = "RECORD_NO, ERPSFD, PURCHASE, CLASS, LOCALE, REGION, UNSPSC_CODE, UNSPSC_DESC, "
                                + "CONCEPT_ID, ORGN_ID, QUALITY_LEVEL, TRUST_LEVEL, ACTIVE_STATUS, CREATE_BY, REFERENCE_NO, "
                                + "REFERENCE_TYPE, VENDOR_NAME, STRIP_REFERENCE_NO, RESEARCH_URL, UOM, MATL_TYPE,INDUSTRY, "
                                + "CATEGORY, EQUIVALENT_REF_NUMBER, ALTERNATE_VENDOR";
                        tableName = "MV_SMART_SEARCH_TEXT_REF";
                    } else {
                        projectionFields = "RECORD_NO, ERPSFD, PURCHASE, CLASS, LOCALE, REGION, UNSPSC_CODE, UNSPSC_DESC, "
                                + "CONCEPT_ID, ORGN_ID, QUALITY_LEVEL, TRUST_LEVEL, ACTIVE_STATUS, CREATE_BY, REFERENCE_NO, "
                                + "REFERENCE_TYPE, VENDOR_NAME, STRIP_REFERENCE_NO, RESEARCH_URL, UOM, MATL_TYPE,INDUSTRY, "
                                + "CATEGORY, EQUIVALENT_REF_NUMBER, ALTERNATE_VENDOR";
                        tableName = "MV_SMART_SEARCH_TEXT_REF";
                    }
//                    projectionFields = "RECORD_NO, ERPSFD, PURCHASE, CLASS, LOCALE, REGION, UNSPSC_CODE, UNSPSC_DESC, CONCEPT_ID, ORGN_ID, QUALITY_LEVEL, TRUST_LEVEL, ACTIVE_STATUS, CREATE_BY, REFERENCE_NO, REFERENCE_TYPE, VENDOR_NAME, STRIP_REFERENCE_NO, RESEARCH_URL, UOM, MATL_TYPE,INDUSTRY, CATEGORY, EQUIVALENT_REF_NUMBER, ALTERNATE_VENDOR";
                }

                String[] projectColsArray = projectionFields.split(",");
                for (Iterator it = jsParamsArray.iterator(); it.hasNext();) {
                    JSONObject paramObj = (JSONObject) it.next();
                    colName = String.valueOf(paramObj.get("column"));
                    datatype = String.valueOf(paramObj.get("datatype"));
                    operatorName = String.valueOf(paramObj.get("operator"));
                    operatorName = operatorName.trim();
                    andOrOperator = String.valueOf(paramObj.get("andOrOperator"));
                    typeSelectStr = String.valueOf(paramObj.get("typeSelectStr"));
                    dlovcolname = String.valueOf(paramObj.get("dlovcolname"));
                    symbol = String.valueOf(paramObj.get("symbol"));
                    opValue = paramObj.get("value") == null ? "" : String.valueOf(paramObj.get("value"));
                    //opValue = visionUtills.strippedValue(opValue);
                    if (domainType != null && !"".equalsIgnoreCase(domainType) && !"null".equalsIgnoreCase(domainType)
                            && "SERVICE".equalsIgnoreCase(domainType)) {
                        colName = "CLASS_TERM";
                    } else if (colName != null && ("TERM".equalsIgnoreCase(colName) || "CLASS_TERM".equalsIgnoreCase(colName))) {
                        colName = "CLASS";
                    }

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
                            typeSelectStr = "'" + typeSelectStr + "'";

                        } else if (operatorName.equalsIgnoreCase("NOT EQUALS")) {
                            if (datatype.equalsIgnoreCase("date")) {
                                colName = " TO_CHAR(" + colName + ",'dd-MM-yyyy')";
                            }
                            operatorName = "<>";
                            opValue = "'" + opValue + "'";
                            typeSelectStr = "'" + typeSelectStr + "'";
                        } else if (operatorName.equalsIgnoreCase("BEGINING WITH")) {
                            opValue = "'" + opValue + "%'";
                            typeSelectStr = "'" + typeSelectStr + "%'";
                            operatorName = " LIKE ";
                        } else if (operatorName.equalsIgnoreCase("ENDING WITH")) {
                            opValue = "'%" + opValue + "'";
                            typeSelectStr = "'%" + typeSelectStr + "'";
                            operatorName = " LIKE ";
                        } else if (operatorName.equalsIgnoreCase("LIKE")) {
                            opValue = "'%" + opValue + "%'";
                            typeSelectStr = "'%" + typeSelectStr + "%'";
                            operatorName = " LIKE ";
                        } else if (operatorName.equalsIgnoreCase("NOT LIKE")) {
                            opValue = "'%" + opValue + "%'";
                            typeSelectStr = "'%" + typeSelectStr + "%'";
                            operatorName = " NOT LIKE ";
                        } else if (operatorName.equalsIgnoreCase("CONTAINING")) {
                            operatorName = " LIKE ";
                            opValue = "'%" + opValue + "%'";
                            typeSelectStr = "'%" + typeSelectStr + "%'";
                        } else if (operatorName.equalsIgnoreCase("IS")) {
                            opValue = " IS NULL";
                            typeSelectStr = " IS NULL";
                            operatorName = "";
                        } else if (operatorName.equalsIgnoreCase("IS NOT")) {
                            opValue = " IS NOT NULL";

                            typeSelectStr = " = "+"'" + typeSelectStr + "'";
                            operatorName = "";


                        } else if (operatorName.equalsIgnoreCase("LESS THAN")) {
                            operatorName = "<";
                            if (datatype.equalsIgnoreCase("date")) {
                                opValue = " TO_DATE('" + opValue + "','dd-MM-yyyy')";
                            } else {
                                opValue = "'" + opValue + "'";
                                typeSelectStr = "'" + typeSelectStr + "'";
                            }
                        } else if (operatorName.equalsIgnoreCase("GREATER THAN")) {
                            operatorName = " > ";
                            if (datatype.equalsIgnoreCase("date")) {
                                opValue = " TO_DATE('" + opValue + "','dd-MM-yyyy')";
                            } else {
                                opValue = opValue.replaceAll(",", "','");
                                System.out.println("in value::::0" + opValue);
                                opValue = " ('" + opValue + "' )";
                                typeSelectStr = " ('" + typeSelectStr + "' )";
                            }

                        } else if (operatorName.equalsIgnoreCase("IN")) {
                            operatorName = " IN ";
                            if (datatype.equalsIgnoreCase("date")) {
                                opValue = " TO_DATE('" + opValue + "','dd-MM-yyyy')";
                            } else {
                                opValue = generateInStr(opValue);
                                typeSelectStr = generateInStr(typeSelectStr);
                            }

                        } else if (operatorName.equalsIgnoreCase("NOT IN")) {
                            operatorName = "NOT IN ";
                            if (datatype.equalsIgnoreCase("date")) {
                                opValue = " TO_DATE('" + opValue + "','dd-MM-yyyy')";
                            } else {
                                opValue = generateInStr(opValue);
                                typeSelectStr = generateInStr(typeSelectStr);
                            }
                        } else if (operatorName.equalsIgnoreCase("BETWEEN")) {
                            String minvalue = paramObj.get("minvalue") == null ? "01-01-1900" : String.valueOf(paramObj.get("minvalue"));
                            String maxvalue = paramObj.get("maxvalue") == null ? "01-01-2200" : String.valueOf(paramObj.get("maxvalue"));
                            colName = "(" + colName;
                            operatorName = " BETWEEN ";
                            if (datatype.equalsIgnoreCase("date")) {
                                opValue = " TO_DATE('" + minvalue + "','dd-MM-yyyy') AND TO_DATE('" + maxvalue + "','dd-MM-yyyy')";
                            } else {
                                opValue = " '" + minvalue + " AND '" + maxvalue + "')";
                            }
                        }
                        System.out.println("query:::" + query);
                        if (!query.contains("WHERE ")) {

                            query = query + " WHERE ";
                        } else {
                            if (andOrOperator != null && !"".equalsIgnoreCase(andOrOperator) && !"null".equalsIgnoreCase(andOrOperator)) {
                                query = query + " " + andOrOperator;
                            } else {
                                query = query + " AND ";
                            }
                        }
                        System.out.println("query::360::" + query);
                        String andOrQuery = "";
                        if (dlovcolname != null && !"".equalsIgnoreCase(dlovcolname) && !"null".equalsIgnoreCase(dlovcolname)) {
                            andOrQuery += " ( " + colName + " " + operatorName + " " + opValue + " " + andOrOperator + " " + dlovcolname + " " + operatorName + " " + typeSelectStr + " ) ";
                            query += andOrQuery;
                        } else {

                            query += " " + colName + " " + operatorName + " " + opValue;
                        }

                        System.out.println("366:::::::::" + query);
                    }
                }
                try {

                    if (domainType != null && !"".equalsIgnoreCase(domainType) && !"null".equalsIgnoreCase(domainType)
                            && "PRODUCT".equalsIgnoreCase(domainType)) {
                        if (query.toUpperCase().contains("WHERE")) {
                            query = query + " AND LOCALE ='" + locale + "'";
                        } else {
                            query = query + " WHERE LOCALE ='" + locale + "'";
                        }
                    }

                    String filterDataString = request.filterData;
                    JSONObject filterData = (JSONObject) new JSONParser().parse(filterDataString);
                    JSONObject jsFilterObj = null;
                    JSONArray jsFilterArray = (JSONArray) filterData.get("filtercols");
                    JSONObject jsSortCol = (JSONObject) filterData.get("sortcol");
                    String sortField = "", sortorder = "", searchValue = "";
                    String searchField = "", filtercondition = "", queryOperator = "";

                    for (Object jsFilterArray1 : jsFilterArray) {
                        if (query.toUpperCase().contains("WHERE")) {
                            queryOperator = " AND ";
                        } else {
                            queryOperator = " WHERE ";
                        }
                        jsFilterObj = (JSONObject) jsFilterArray1;
                        searchValue = (String) jsFilterObj.get("value");
                        searchField = (String) jsFilterObj.get("column");
                        if (domainType != null && !"".equalsIgnoreCase(domainType) && !"null".equalsIgnoreCase(domainType)
                                && "SERVICE".equalsIgnoreCase(domainType)) {
                            searchField = "CLASS_TERM";
                        }
                        if (searchField != null && ("TERM".equalsIgnoreCase(searchField) || "CLASS_TERM".equalsIgnoreCase(searchField))) {
                            searchField = "CLASS";
                        }
                        filtercondition = jsFilterObj.get("condition") == null ? "CONTAINS" : String.valueOf(jsFilterObj.get("condition"));
                        if (searchField.toUpperCase().contains("DATE")) {
                            if (filtercondition.equalsIgnoreCase("LESS_THAN_OR_EQUAL")) {
                                query += queryOperator + "   " + searchField + " "
                                        + " <= TO_DATE('" + searchValue + "','dd-MM-yyyy')";
                            } else if (filtercondition.equalsIgnoreCase("GREATER_THAN_OR_EQUAL")) {
                                query += queryOperator + "   " + searchField + " "
                                        + " >= TO_DATE('" + searchValue + "','dd-MM-yyyy')";
                            }
                        } else {

                            searchValue = searchValue.toUpperCase();
                            switch (filtercondition) {
                                case "NOT_EMPTY":
                                    query += queryOperator + searchField + " <> ''";
                                    break;
                                case "NOT_NULL":
                                    query += queryOperator + searchField + " NOT NULL";
                                    break;
                                case "EMPTY":
                                    query += queryOperator + searchField + " = ''";
                                    break;
                                case "NULL":
                                    query += queryOperator + " " + searchField + " IS NULL";
                                    break;
                                case "CONTAINS_CASE_SENSITIVE":
                                    query += queryOperator + "   " + searchField + " LIKE '%" + searchValue + "%'";
                                    break;
                                case "CONTAINS":
                                    searchValue = searchValue.replaceAll("//s", "%") + "%";
                                    query += queryOperator + " " + searchField + " LIKE '%" + searchValue + "%'";
                                    break;
                                case "DOES_NOT_CONTAIN_CASE_SENSITIVE":
                                    query += queryOperator + "  " + searchField + " NOT LIKE '%" + searchValue + "%'";
                                    break;
                                case "DOES_NOT_CONTAIN":
                                    query += queryOperator + " " + searchField + " NOT LIKE '%" + searchValue + "%'";
                                    break;
                                case "EQUAL_CASE_SENSITIVE":
                                    query += queryOperator + "  " + searchField + " = '" + searchValue + "'";
                                    break;
                                case "EQUAL":
                                    query += queryOperator + searchField + " = '" + searchValue + "'";
                                    break;
                                case "NOT_EQUAL_CASE_SENSITIVE":
                                    query += queryOperator + "  " + searchField + " <> '" + searchValue + "'";
                                    break;
                                case "NOT_EQUAL":
                                    query += queryOperator + " " + searchField + " <> '" + searchValue + "'";
                                    break;
                                case "GREATER_THAN":
                                    query += queryOperator + " " + searchField + " > '" + searchValue + "'";
                                    break;
                                case "LESS_THAN":
                                    query += queryOperator + " " + searchField + " < '" + searchValue + "'";
                                    break;
                                case "GREATER_THAN_OR_EQUAL":
                                    query += queryOperator + " " + searchField + " >= '" + searchValue + "'";
                                    break;
                                case "LESS_THAN_OR_EQUAL":
                                    query += queryOperator + " " + searchField + " <= '" + searchValue + "'";
                                    break;
                                case "STARTS_WITH_CASE_SENSITIVE":
                                    query += queryOperator + "  " + searchField + " LIKE '" + searchValue + "%'";
                                    break;
                                case "STARTS_WITH":
                                    query += queryOperator + " " + searchField + " LIKE '" + searchValue + "%'";
                                    break;
                                case "ENDS_WITH_CASE_SENSITIVE":
                                    query += queryOperator + "  " + searchField + " LIKE '%" + searchValue + "'";
                                    break;
                                case "ENDS_WITH":
                                    query += queryOperator + " " + searchField + " LIKE '%" + searchValue + "'";
                                    break;
                            }
                        }
                    }
                    countQuery = "select count(*) from " + query;
                    if (!String.valueOf(jsSortCol.get("sortcol")).equalsIgnoreCase("")
                            && !String.valueOf(jsSortCol.get("sortcol")).equalsIgnoreCase("null")) {
                        sortField = (String) jsSortCol.get("sortcol");
                        sortorder = (String) jsSortCol.get("sortorder");
                        query = query + " order by " + sortField + " " + sortorder;
                    }

                } catch (Exception e) {
                }


                countQuery = " select count(*) FROM "+tableName+"  " + query;

                long count = mvSmartSearchTextRefRepository.countRecords(countQuery);
                query = "select ROWNUM as RNUM," + projectionFields + " FROM "+tableName+" " + query;
                query = "select * from ( " + query + ") where RNUM between " + (recordStartIndex + 1) + " AND " + recordEndIndex;

               List<Object[]> results = mvSmartSearchTextRefRepository.findPagedRecords(query);
               System.out.println(query);
                System.out.println(results);
                String projectionCol = "";
                for (Object[] result : results) {
                    JSONObject jsResultsObject = new JSONObject();
                    int index = 1;
                    for (String projectColsArray1 : projectColsArray) {
                         projectionCol = projectColsArray1.trim();

                        if (result[index] == null) {
                            jsResultsObject.put(projectionCol, "");
                            if (projectionCol != null && "CLASS".equalsIgnoreCase(projectionCol)) {
                                jsResultsObject.put("CLASS_TERM", "");
                                jsResultsObject.put("TERM", "");
                            }
                        } else {
                            Object item = result[index];
                            if (item instanceof byte[]) {
                                String orgnIdHexString = bytesToString((byte[]) item);
                               jsResultsObject.put(projectionCol, orgnIdHexString);
     //                  jsResultsObject.put(projectionCol,item);
                            } else if (item instanceof String) {
                                jsResultsObject.put(projectionCol, (String) item);
                            } else if (item instanceof Character) {
                                jsResultsObject.put(projectionCol, item.toString());
                            } else {
                                throw new IllegalArgumentException("Unsupported data type: " + item.getClass());
                            }
                //            jsResultsObject.put(projectionCol,  item);
                            if ("CLASS".equalsIgnoreCase(projectionCol)) {
                                jsResultsObject.put("CLASS_TERM", result[index]);
                                jsResultsObject.put("TERM", result[index]);
                            }
                        }

                        index++;
                        jsResultsObject.put("totalRecords", count);
                        jsResultsObject.put("TotalRows", count);
                    }
                    String erp = request.getErp();
                    if(erp.equalsIgnoreCase("sap_format")) {
                        jsResultsObject = convertSapFormat(request, jsResultsObject);
                    }
                    jsResultsArray.add(jsResultsObject);
                }
                jsResponseObject.put("responseData", jsResultsArray);
                repSearch.responseData = jsResultsArray.toJSONString();
                logger.info("repSearch.responseData::" + repSearch.responseData);
                System.out.println("repSearch.responseData::" + repSearch.responseData);
                jsResponseObject.put("message", "Success");
                repSearch.message = "Success";
            } catch (Exception e) {
                jsResponseObject.put("message", e);
                logger.severe("Error processing result data");
                e.printStackTrace();

            }

        } catch (ParseException ex) {
            repSearch.message = "Please send search data in JSON string format::[{'datatype':'string','column':'REFERENCE_NO','rangeflag':'N','minvalue':'','maxvalue':'','value':'1234','operator':'CONTAINING'}]";
            jsResponseObject.put("message", "Please send search data in JSON string format::[{'datatype':'string','column':'REFERENCE_NO','rangeflag':'N','minvalue':'','maxvalue':'','value':'1234','operator':'CONTAINING'}]");
            logger.severe("Error parsing search data");
            ex.printStackTrace();
        }

        jsResponseObject.put("wsstatus", "success");

        return jsResponseObject.toJSONString();

}

    private JSONObject convertSapFormat(RepositorySearchRequest request, JSONObject jsResultsObject) {
        JSONObject resultObj = new JSONObject();
        String useColumn = "CLASS_TERM, RECORD_NO, VENDOR_NAME, REFERENCE_TYPE, STRIP_REFERENCE_NO, SHORT_TEXT, PURCHASE";
        String jsonString = "{\"name\":\"\",\"equipmentId\":\"\",\"internalId\":\"\",\"manufacturerName\":\"\",\"modelName\":\"\",\"manufacturerPartNumber\":\"\",\"shortDesc\":\"\",\"longDesc\":\"\",\"soldTo\":null,\"hasInrevision\":false,\"batchNumber\":\"\",\"equipmentVersion\":0,\"status\":\"\",\"additionalBusinessPartners\":[],\"templates\":[],\"buildDate\":null,\"classId\":null,\"class\":null,\"completeness\":null,\"objectType\":\"\",\"distributionChannel\":null,\"division\":null,\"firmwareVersion\":null,\"keywords\":null,\"lifeCycle\":\"1\",\"modelId\":null,\"operatorID\":null,\"plannerGroup\":null,\"planningPlant\":null,\"procurementNumber\":null,\"salesGroup\":null,\"salesOffice\":null,\"salesOrganization\":null,\"secondaryKey\":null,\"serialNumber\":\"\",\"sourceBPRole\":\"\",\"subclassId\":null,\"subclass\":null,\"tagNumber\":null,\"tin\":null,\"operatorName\":\"\",\"isOperatorValid\":null,\"isRoot\":null,\"UID\":null,\"baseline\":\"0\",\"manufacturerId\":null,\"source\":\"PILOGPPRM\",\"modelKnown\":true,\"modelRequestID\":null,\"publishedOn\":\"\",\"hasERPinfoAccess\":null,\"productRelevance\":null,\"equipmentImageUrl\":null,\"externalSystemID\":null,\"externalSystemName\":null,\"maintenancePlant\":\"\",\"maintenanceWorkCenter\":null,\"plant\":\"\",\"rootEquipmentID\":null,\"category\":null,\"weight\":null,\"weightUom\":null,\"size\":null,\"manufacturerCountry\":null,\"customerWarrantyStartDate\":null,\"customerWarrantyEndDate\":null,\"customerMasterWarranty\":null,\"customerInheritWarranty\":null,\"customerPassOnWarranty\":null,\"manufacturerWarrantyStartDate\":null,\"manufacturerWarrantyEndDate\":null,\"manufacturerMasterWarranty\":null,\"manufacturerInheritWarranty\":null,\"manufacturerPassOnWarranty\":null,\"erpObjectType\":\"\",\"authorizationGroup\":\"\",\"inventoryNumber\":null,\"startupDate\":null,\"shiftNoteType\":null,\"acquisitionValue\":null,\"acquisitionValueUom\":null,\"acquisitionDate\":null,\"location\":null,\"room\":null,\"plantSection\":null,\"workCenter\":null,\"abcIndicator\":null,\"sortField\":null,\"companyCode\":\"\",\"businessArea\":\"\",\"assetMainNumber\":null,\"assetSubNumber\":null,\"costCenter\":\"\",\"wbsElement\":null,\"standingOrder\":null,\"settlementOrder\":null,\"catalogProfile\":null,\"constructionType\":null,\"material\":null,\"operatorSerialNumber\":null,\"uii\":null,\"iuidType\":null,\"responsiblePlantUii\":null,\"lastSerialNumber\":null,\"controllingArea\":\"\"}";
         resultObj = (JSONObject) JSONValue.parse(jsonString);
        resultObj.put("name", jsResultsObject.get("CLASS_TERM")!=null ? jsResultsObject.get("CLASS_TERM"): jsResultsObject.get("CLASS"));
        resultObj.put("equipmentId", jsResultsObject.get("RECORD_NO"));
        resultObj.put("internalId", jsResultsObject.get("RECORD_NO"));
        resultObj.put("manufacturerName", jsResultsObject.get("VENDOR_NAME")!=null ? jsResultsObject.get("VENDOR_NAME"): null);
        resultObj.put("modelName", jsResultsObject.get("REFERENCE_TYPE")!=null ? jsResultsObject.get("REFERENCE_TYPE"): null);
        resultObj.put("manufacturerPartNumber", jsResultsObject.get("STRIP_REFERENCE_NO")!=null ? jsResultsObject.get("STRIP_REFERENCE_NO"): null);
        resultObj.put("shortDesc", jsResultsObject.get("SHORT_TEXT")!=null ? jsResultsObject.get("SHORT_TEXT"): null);
        resultObj.put("longDesc", jsResultsObject.get("PURCHASE")!=null ? jsResultsObject.get("PURCHASE"): jsResultsObject.get("PURCHASE_TEXT"));
        for (Object keys : jsResultsObject.keySet()) {
            String key = (String) keys;
            Set<String> keysToUse = new HashSet<>(Arrays.asList(useColumn.split(",\\s*")));
            if (!keysToUse.contains(key)) {
                String[] parts = key.split("_");
                StringBuilder result = new StringBuilder(parts[0].toLowerCase());
                for (int i = 1; i < parts.length; i++) {
                    result.append(Character.toUpperCase(parts[i].charAt(0)))
                            .append(parts[i].substring(1).toLowerCase());
                }
                String keyCase = result.toString();
                resultObj.put(keyCase, jsResultsObject.get(key));
            }
        }
       return resultObj;
    }

    public String generateInStr(String value) {
        try {
            logger.info("value:::Before:::" + value);
            if (value != null && !"".equalsIgnoreCase(value)) {
                if (value != null && value.contains(",") && value.contains("','")) {
                    value = "(" + value + ")";
                } else if (value.contains(",")) {
                    value = value.replaceAll(" ", "");
                    logger.info("value is :::" + value);
                    value = "('" + value.replaceAll(",", "','") + "')";
                } else {
                    value = "('" + value + "')";
                }
            } else {
                value = "('')";
            }

            logger.info("value:::After:::" + value);
        } catch (Exception e) {
            logger.severe("Error in generateInStr");
            e.printStackTrace();
        }

        return value;
    }
    private static String bytesToString(byte[] bytes) {
        try {
            StringBuilder stringBuilder = new StringBuilder(2 * bytes.length);
            for (byte b : bytes) {
                stringBuilder.append(String.format("%02x", b).toUpperCase());
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            logger.severe("Error in bytesToString");
            e.printStackTrace();
            return ""; // Or handle the error as needed
        }
    }


}

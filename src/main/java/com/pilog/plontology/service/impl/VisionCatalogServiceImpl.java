package com.pilog.plontology.service.impl;

import com.pilog.plontology.model.pprm.*;
import com.pilog.plontology.payloads.DataResponse;
import com.pilog.plontology.payloads.ResponseDTO;
import com.pilog.plontology.repository.pprm.*;
import com.pilog.plontology.service.IVisionCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisionCatalogServiceImpl implements IVisionCatalogService {

    @Autowired
    private RecordMasterRepository recordMasterRepository;

    @Autowired
    private RecordDocumentRepository recordDocumentRepository;

    @Autowired
    private RecordTextRepository recordTextRepository;

    @Autowired
    private RecordReferenceRepository recordReferenceRepository;

    @Autowired
    private RecordCharRepository recordCharRepository;
    @Override
    public ResponseDTO VisionMasterQuery(String recordNo) {
        List<DataResponse> dataMasterResponseList = new ArrayList<>();
        List<RecordMaster> recordsList = recordMasterRepository.findByRecordNo(recordNo);
        for (RecordMaster masterResultSet:recordsList) {
            DataResponse responseMaster = new DataResponse();
            responseMaster.setRecordNo(masterResultSet.getRecordNo());
            responseMaster.setErpNo(masterResultSet.getErpNo());
            responseMaster.setDrId1(bytesToHex(masterResultSet.getDrId1()));
            //response.setDrId2(masterResultSet.getString("DR_ID2"));
            // response.setRecordType((masterResultSet.getString("RECORD_TYPE") != null ? masterResultSet.getString("RECORD_TYPE") : "") );
//               response.setDrId4(masterResultSet.getString("DR_ID4"));
//               response.setDrId5(masterResultSet.getString("DR_ID5"));
            responseMaster.setRecordType(masterResultSet.getRecordType());
            responseMaster.setRecordGroup(masterResultSet.getRecordGroup());
            responseMaster.setUom(masterResultSet.getUom());
            responseMaster.setRegion(masterResultSet.getRegion());
            responseMaster.setLocale(masterResultSet.getLocale());
            responseMaster.setShortDesc(masterResultSet.getShortDesc()!= null ? masterResultSet.getShortDesc() : "");
            responseMaster.setStatus(masterResultSet.getStatus());
            responseMaster.setClassTerm(masterResultSet.getClassTerm());
            responseMaster.setDataSource(masterResultSet.getDataSource());
            responseMaster.setDataSource1(masterResultSet.getDataSource1());
            dataMasterResponseList.add(responseMaster);
        }
        return null;
    }

    @Override
    public ResponseDTO VisionDocumentQuery(String recordNo) {

        ResponseDTO responseDTO = new ResponseDTO();
        List<DataResponse> dataResponseDocumentList = new ArrayList<>();
       List<RecordDocument> documentResultList = recordDocumentRepository.findByRecordNo(recordNo);
        for (RecordDocument documentResultSet:documentResultList) {
            DataResponse responseDocument = new DataResponse();
            responseDocument.setRecordNo(documentResultSet.getRecordNo());
            responseDocument.setBusinessUnit(documentResultSet.getBusinessUnit());
            responseDocument.setInstance(documentResultSet.getInstance());
            responseDocument.setDocumentNo(documentResultSet.getDocumentNo());
            responseDocument.setDocumentType(documentResultSet.getDocumentType());
            responseDocument.setRevision(documentResultSet.getRevision());
            responseDocument.setDocumentItem(documentResultSet.getDocumentItem());
            responseDocument.setItemPosition(documentResultSet.getItemPosition());
            responseDocument.setVendorId(documentResultSet.getVendorId());
            responseDocument.setVendorName(documentResultSet.getVendorName());
            responseDocument.setStxtFlag(documentResultSet.getStxtFlag());
            responseDocument.setLtxtFlag(documentResultSet.getLtxtFlag());
            responseDocument.setStripDocumentNo(documentResultSet.getStripDocumentNo());
            dataResponseDocumentList.add(responseDocument);
        }
        responseDTO.setDocumentDataList(dataResponseDocumentList);

        return responseDTO;
    }

    @Override
    public ResponseDTO VisionTextQuery(String recordNo) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<DataResponse> dataResponseTextList = new ArrayList<>();
        List<RecordText> TextResultList = recordTextRepository.findByRecordNo(recordNo);
        for (RecordText textResultSet:TextResultList) {
            DataResponse responseText = new DataResponse();
            responseText.setRecordNo(textResultSet.getRecordNo());
            responseText.setBusinessUnit(textResultSet.getBusinessUnit());
            responseText.setLocale(textResultSet.getLocale());
            responseText.setType(textResultSet.getType());
            responseText.setRegion(textResultSet.getRegion());
            responseText.setText(textResultSet.getText());
            dataResponseTextList.add(responseText);
        }
        responseDTO.setTextDataList(dataResponseTextList);
        return responseDTO;
    }

    @Override
    public ResponseDTO VisionReferenceQuery(String recordNo) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<DataResponse> dataResponseReferenceList = new ArrayList<>();
        List<RecordReference> ReferenceResultList = recordReferenceRepository.findByRecordNo(recordNo);
        for (RecordReference referenceResultSet:ReferenceResultList) {
            DataResponse responseReference = new DataResponse();
            responseReference.setRecordNo(referenceResultSet.getRecordNo());
            responseReference.setBusinessUnit(referenceResultSet.getBusinessUnit());
            responseReference.setLocale(referenceResultSet.getLocale());
            responseReference.setReferenceNo(referenceResultSet.getReferenceNo());
            responseReference.setRegion(referenceResultSet.getRegion());
            responseReference.setReferenceType(referenceResultSet.getReferenceType());
            responseReference.setVendorId(referenceResultSet.getVendorId());
            responseReference.setVendorName(referenceResultSet.getVendorName());
            responseReference.setStxtFlag(referenceResultSet.getStxtFlag());
            responseReference.setLtxtFlag(referenceResultSet.getLtxtFlag());
            responseReference.setBrandName(referenceResultSet.getBrandName() != null ? referenceResultSet.getBrandName() : "");
            responseReference.setStripReferenceNo(referenceResultSet.getStripReferenceNo());
            responseReference.setSupersededReference(referenceResultSet.getSupersededReference() != null ? referenceResultSet.getSupersededReference(): "");
            dataResponseReferenceList.add(responseReference);
        }
        responseDTO.setReferenceDataList(dataResponseReferenceList);
        return responseDTO;
    }

    @Override
    public ResponseDTO VisionCharQuery(String recordNo) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<DataResponse> dataResponseCharactersticsList = new ArrayList<>();
        List<RecordChar> charResultList = recordCharRepository.findByRecordNo(recordNo);
        for (RecordChar characteristicsResultSet:charResultList) {
            DataResponse responseCharacteristics = new DataResponse();
            responseCharacteristics.setRecordNo(characteristicsResultSet.getRecordNo());
            responseCharacteristics.setClassConceptId(characteristicsResultSet.getClassConceptId());
            responseCharacteristics.setLocale(characteristicsResultSet.getLocale());
            responseCharacteristics.setPropertyName(characteristicsResultSet.getPropertyName());
            responseCharacteristics.setPropertyConceptId(characteristicsResultSet.getPropertyConceptId());
            responseCharacteristics.setRegion(characteristicsResultSet.getRegion());
            responseCharacteristics.setPropertyValue1(characteristicsResultSet.getPropertyValue1());
            responseCharacteristics.setPropertyValue2(characteristicsResultSet.getPropertyValue2() != null ? characteristicsResultSet.getPropertyValue2() : "");
            responseCharacteristics.setPropUomConceptId(characteristicsResultSet.getPropUomConceptId());
            responseCharacteristics.setPropertyUom(characteristicsResultSet.getPropertyUom());
            responseCharacteristics.setStxtFlag(characteristicsResultSet.getStxtFlag());
            responseCharacteristics.setLtxtFlag(characteristicsResultSet.getLtxtFlag());
            responseCharacteristics.setDataType(characteristicsResultSet.getDataType());
            responseCharacteristics.setShortSeq(String.valueOf(characteristicsResultSet.getShortSeq()));
            responseCharacteristics.setLongSeq(String.valueOf(characteristicsResultSet.getLongSeq()));
            responseCharacteristics.setRequiredFlag(characteristicsResultSet.getRequiredFlag());
            dataResponseCharactersticsList.add(responseCharacteristics);
        }
        responseDTO.setCharacteristicsDataList(dataResponseCharactersticsList);
        return responseDTO;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}

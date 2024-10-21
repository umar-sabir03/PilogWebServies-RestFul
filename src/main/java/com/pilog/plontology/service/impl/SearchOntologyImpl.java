package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.Ontology;
import com.pilog.plontology.payloads.OntologyRequest;
import com.pilog.plontology.payloads.OntologyResponse;
import com.pilog.plontology.repository.apex.OntologyRepository;
import com.pilog.plontology.service.ISearchOntology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchOntologyImpl implements ISearchOntology {
    @Autowired
    private OntologyRepository ontologyRepository;

    @Override
    public OntologyResponse list(OntologyRequest request) {
    OntologyResponse response = new OntologyResponse();
    List<Ontology> classDataList=new ArrayList<>();

    String classParam = (request.getClazz() == null || request.getClazz().isEmpty()) ? "*" : request.getClazz().trim();
    String conceptIdParam = (request.getConceptId() == null || request.getConceptId().isEmpty()) ? "" : request.getConceptId().trim();

    List<Object[]> results = ontologyRepository.findOntologyData(classParam, conceptIdParam);
        for (Object[] row : results) {
        Ontology data = new Ontology();
            byte[] drIdBytes = (byte[]) row[0];
            String drId = drIdBytes != null ? bytesToHex(drIdBytes) : "";
            data.setDrId(drId);
            data.setClazz(row[1] != null ? row[1].toString() : "");
        data.setConceptId(row[2] != null ? row[2].toString() : "");
        data.setOAbbr(row[3] != null ? row[3].toString() : "");
        data.setUnspscCode(row[4] != null ? row[4].toString() : "");
        data.setUnspscDesc(row[5] != null ? row[5].toString() : "");

            classDataList.add(data);
    }

        response.setClassDataList(classDataList);

        return response;
}
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}

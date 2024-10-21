package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.CharacteristicERP;
import com.pilog.plontology.payloads.CharacteristicRequest;
import com.pilog.plontology.service.ICharacteristicService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CharacteristicServiceImpl implements ICharacteristicService {
    @PersistenceContext(unitName = "pprmEntityManagerFactory")
    private EntityManager pprmEntityManager;

    @PersistenceContext(unitName = "apexEntityManagerFactory")
    private EntityManager apexProdEntityManager;
    @Override
    public List<CharacteristicERP> duplicateRecords(CharacteristicRequest request) {
        List<CharacteristicERP> list = new ArrayList<>();
        request.setOrgnId(setPprIfOrgnIdNotValid(request.getOrgnId()));

        try {
            String query = "SELECT CLASS_TERM, PROPERTY_TERM, TERM AS O_VALUE, ABRREVIATION, UPPER(PROPERTY_UOM) " +
                    "FROM DR_CHAR_VALUES A " +
                    "JOIN DATA_REQUIREMENT B ON A.CLASS_CONCEPT_ID = B.CLASS_CONCEPT_ID AND A.PROPERTY_CONCEPT_ID = B.PROPERTY_CONCEPT_ID " +
                    "JOIN O_RECORD_CHAR C ON A.CLASS_CONCEPT_ID = C.CLASS_CONCEPT_ID AND A.PROPERTY_CONCEPT_ID = C.PROPERTY_CONCEPT_ID AND A.VALUE_CONCEPT_ID = C.PROP_VALUE_CONCEPT_ID " +
                    "WHERE UPPER(CLASS_TERM) = UPPER(:classTerm) " +
                    "AND UPPER(PROPERTY_TERM) = UPPER(:propertyTerm) " +
                    "AND UPPER(ORGN_ID) = UPPER(:orgnId) " +
                    "GROUP BY CLASS_TERM, PROPERTY_TERM, TERM, ABRREVIATION, PROPERTY_UOM";

            Query nativeQuery = pprmEntityManager.createNativeQuery(query);
            nativeQuery.setParameter("classTerm", request.getClasss());
            nativeQuery.setParameter("propertyTerm", request.getProperty());
            nativeQuery.setParameter("orgnId", request.getOrgnId());

            List<Object[]> results = nativeQuery.getResultList();
            for (Object[] row : results) {
                CharacteristicERP erp = new CharacteristicERP();
                erp.setODescriptor((String) row[0]);
                erp.setWord((String) row[1]);
                erp.setOValue((String) row[2]);
                erp.setOUom((String) row[4]);
                list.add(erp);
            }

        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }


    private String setPprIfOrgnIdNotValid(String orgnId) {
        if ((!isValidRaw(orgnId))) {
            orgnId = "2F57487BE2F6474BE053270110ACD546";
        }
        return orgnId;
    }

    private boolean isValidRaw(String orgnId) {
        boolean valid = false;
        if (orgnId.matches("[A-Z0-9]{32}")) {
            System.out.println("is valid hex");
            valid = (IsApex2POrganization(orgnId)) || (IsPprmOrganization(orgnId));
        }
        System.out.println("checking availability of given orgn Id ::" + orgnId + ",valid :: " + valid);
        return valid;
    }

    private boolean IsPprmOrganization(String orgnId) {
        try {

            //PDBVISION
            String query = "SELECT DISTINCT A.ORG_ID FROM ORG_STRUCTURE A WHERE A.ORG_ID = :orgnId";

            Query nativeQuery = pprmEntityManager.createNativeQuery(query);
            nativeQuery.setParameter("orgnId", orgnId);
            List<String> results = nativeQuery.getResultList();
            return !results.isEmpty();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    private boolean IsApex2POrganization(String orgnId) {
        try {
            // apex production
            String query = "SELECT DISTINCT A.ORGN_ID FROM ORGN_STRUCTURE A WHERE A.ORGN_ID = :orgnId";
            Query nativeQuery = apexProdEntityManager.createNativeQuery(query);
            nativeQuery.setParameter("orgnId", orgnId);
            List<String> results = nativeQuery.getResultList();
            if (!results.isEmpty()) {
                return true;
            }
            System.out.println("Is not PPRM");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}

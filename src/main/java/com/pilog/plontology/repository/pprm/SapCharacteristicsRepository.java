package com.pilog.plontology.repository.pprm;

import com.pilog.plontology.payloads.SAPCharacteristicsERP;
import com.pilog.plontology.payloads.SearchSAPCharacteristicsReqDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SapCharacteristicsRepository {

    @PersistenceContext(unitName = "pprmEntityManagerFactory")
    private EntityManager entityManager;

    public List<SAPCharacteristicsERP> searchSAPCharacteristics(SearchSAPCharacteristicsReqDTO searchSAPCharacteristicsReqDTO) {

        List<SAPCharacteristicsERP> list = new ArrayList<>();

        String sql = "SELECT TERM AS O_VALUE, ABRREVIATION AS VALUE_ABBR " +
                "FROM DR_CHAR_VALUES A, DATA_REQUIREMENT B " +
                "WHERE A.CLASS_CONCEPT_ID = B.CLASS_CONCEPT_ID " +
                "AND A.PROPERTY_CONCEPT_ID = B.PROPERTY_CONCEPT_ID " +
                "AND UPPER(CLASS_TERM) = UPPER(:classTerm) " +
                "AND UPPER(PROPERTY_TERM) = UPPER(:propertyTerm) " +
                "GROUP BY CLASS_TERM, PROPERTY_TERM, TERM, ABRREVIATION " +
                "UNION " +
                "SELECT UNIQUE PROPERTY_VALUE1, PROPERTY_VALUE1 " +
                "FROM STG_DOTL_VALUES " +
                "WHERE UPPER(CLASS_TERM) = UPPER(:classTerm) " +
                "AND UPPER(PROPERTY_TERM) = UPPER(:propertyTerm)";

        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("classTerm", searchSAPCharacteristicsReqDTO.getItemRef());
            query.setParameter("propertyTerm", searchSAPCharacteristicsReqDTO.getProperty());

            List<Object[]> results = query.getResultList();

            for (Object[] result : results) {
                SAPCharacteristicsERP erp = new SAPCharacteristicsERP();
                erp.setAtwrt((String) result[0]);
                erp.setValueAbbr((String) result[1]);
                list.add(erp);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
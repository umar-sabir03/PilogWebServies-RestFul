package com.pilog.plontology.repository.pprm;


import com.pilog.plontology.model.pprm.DrCharValues;
import com.pilog.plontology.model.pprm.DrCharValuesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DrCharValuesRepository extends JpaRepository<DrCharValues, DrCharValuesId> {


    @Query(value = "SELECT VALUE_CONCEPT_ID FROM DR_CHAR_VALUES ORDER BY VALUE_CONCEPT_ID DESC FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
    Optional<String> findLastValueConceptIdNative();
    Optional<String> findFirstByOrderByIdValueConceptIdDesc();
    @Query("SELECT COUNT(d) FROM DrCharValues d WHERE d.id.classConceptId = :classConceptId "
            + "AND d.id.propertyConceptId = :propertyConceptId "
            + "AND d.id.term = :term "
            + "AND d.id.languageId = :languageId")
    long countByValues(@Param("classConceptId") String classConceptId,
                       @Param("propertyConceptId") String propertyConceptId,
                       @Param("term") String term,
                       @Param("languageId") String languageId);


}

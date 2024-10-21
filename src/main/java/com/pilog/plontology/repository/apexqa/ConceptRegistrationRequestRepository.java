package com.pilog.plontology.repository.apexqa;

import com.pilog.plontology.model.apexqa.ConceptRegistrationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConceptRegistrationRequestRepository extends JpaRepository<ConceptRegistrationRequest, Integer> {
    @Query("SELECT c.drId, c.irdi FROM ConceptRegistrationRequest c " +
            "WHERE c.conceptTypeId = :conceptTypeId " +
            "AND c.languageId = :languageId " +
            "AND c.orgId = :orgnId " +
            "AND c.term = :term " +
            "AND c.statusRc = 'APPROVED'")
    List<Object[]> findApprovedConcepts(@Param("conceptTypeId") String conceptTypeId,
                                        @Param("languageId") String languageId,
                                        @Param("orgnId") String orgnId,
                                        @Param("term") String term);

    @Query("SELECT c.drId FROM ConceptRegistrationRequest c WHERE c.conceptTypeId = :conceptTypeId "
            + "AND c.languageId = :languageId AND c.orgId = :orgnId "
            + "AND c.term = :term AND c.statusRc = :statusRc AND c.drId IS NOT NULL")
    List<String> findDrIds(@Param("conceptTypeId") String conceptTypeId,
                           @Param("languageId") String languageId,
                           @Param("orgnId") String orgnId,
                           @Param("term") String term,
                           @Param("statusRc") String statusRc);

    @Query("SELECT c.irdi FROM ConceptRegistrationRequest c WHERE c.conceptTypeId = :conceptTypeId "
            + "AND c.languageId = :languageId AND c.orgId = :orgnId "
            + "AND c.term = :term AND c.statusRc = :statusRc AND c.irdi IS NOT NULL")
    List<String> findIrdIds(@Param("conceptTypeId") String conceptTypeId,
                            @Param("languageId") String languageId,
                            @Param("orgnId") String orgnId,
                            @Param("term") String term,
                            @Param("statusRc") String statusRc);
}

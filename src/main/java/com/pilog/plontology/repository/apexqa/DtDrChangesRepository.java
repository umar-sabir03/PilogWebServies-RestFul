package com.pilog.plontology.repository.apexqa;

import com.pilog.plontology.model.apexqa.DtDrChanges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DtDrChangesRepository extends JpaRepository<DtDrChanges, String> {

    @Transactional
    @Modifying
    @Query("UPDATE DtDrChanges d SET d.state = 'DL' WHERE d.drId = :drId")
    int updateStateByOrgIdAndDrId(String drId);

}

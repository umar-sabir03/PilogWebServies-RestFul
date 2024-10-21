package com.pilog.plontology.repository.pprm;

import com.pilog.plontology.model.pprm.WsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface WsHistoryRepo extends JpaRepository<WsHistory,String> {
    List<WsHistory> findByOrgnIdAndAccessDateAndWsIdIn(String orgnId, Date currentDate, List<String> auditId);

    @Query("SELECT w FROM WsHistory w " +
            "WHERE w.orgnId = :orgnId " +
            "AND w.accessDate = :accessDate " +
            "AND w.wsId IN (:wsIds)")
    List<WsHistory> getNumberOfHitsPerDay(String orgnId, Date accessDate, List<String> wsIds);
}

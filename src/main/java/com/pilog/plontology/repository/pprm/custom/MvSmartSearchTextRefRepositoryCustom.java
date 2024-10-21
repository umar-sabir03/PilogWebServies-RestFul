package com.pilog.plontology.repository.pprm.custom;

import java.sql.SQLException;
import java.util.List;

public interface MvSmartSearchTextRefRepositoryCustom {
    long countRecords(String countQuery) throws SQLException;
    List<Object[]> findPagedRecords(String nativeQuery) throws SQLException;
}

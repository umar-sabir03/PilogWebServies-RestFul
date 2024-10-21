package com.pilog.plontology.repository.pprm.custom;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

@Component
public class MvSmartSearchTextRefRepositoryCustomImpl implements MvSmartSearchTextRefRepositoryCustom {
    @PersistenceContext(unitName = "pprmEntityManagerFactory")
    private EntityManager pprmEntityManager;
//    @Override
//    public ResultSet countRecords(String condition) throws SQLException {
//        Connection connection = pprmEntityManager.unwrap(Connection.class);
//        Statement statement = connection.createStatement();
//        return statement.executeQuery(condition);
//    }

//    @Override
//    public long countRecords(String countQuery) throws SQLException {
//        Query query = pprmEntityManager.createNativeQuery(countQuery);
//        return ((Number) query.getSingleResult()).longValue();
//        }
//
//    @Override
//    public List<Object[]> findPagedRecords(String nativeQuery) throws SQLException {
//        Query query = pprmEntityManager.createNativeQuery(nativeQuery);
//        return query.getResultList();
//    }

@Override
public long countRecords(String countQuery) throws SQLException {
    Query query = pprmEntityManager.createNativeQuery(countQuery);
    return ((Number) query.getSingleResult()).longValue();
}


    @Override
    public List<Object[]> findPagedRecords(String nativeQuery) throws SQLException {
        Query query = pprmEntityManager.createNativeQuery(nativeQuery);
        return query.getResultList();
    }
}

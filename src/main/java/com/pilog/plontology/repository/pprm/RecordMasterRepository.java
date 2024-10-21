package com.pilog.plontology.repository.pprm;

import com.pilog.plontology.model.pprm.RecordMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordMasterRepository extends JpaRepository<RecordMaster,String> {
    List<RecordMaster> findByRecordNo(String recordNo);
}

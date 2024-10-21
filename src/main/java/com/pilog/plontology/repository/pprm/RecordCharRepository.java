package com.pilog.plontology.repository.pprm;

import com.pilog.plontology.model.pprm.RecordChar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordCharRepository extends JpaRepository<RecordChar,String> {
    List<RecordChar> findByRecordNo(String recordNo);
}

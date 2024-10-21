package com.pilog.plontology.repository.pprm;

import com.pilog.plontology.model.pprm.RecordText;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordTextRepository extends JpaRepository<RecordText,String> {
    List<RecordText> findByRecordNo(String recordNo);
}

package com.pilog.plontology.repository.pprm;

import com.pilog.plontology.model.pprm.RecordReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordReferenceRepository extends JpaRepository<RecordReference,String> {
    List<RecordReference> findByRecordNo(String recordNo);
}

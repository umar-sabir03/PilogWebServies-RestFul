package com.pilog.plontology.repository.pprm;

import com.pilog.plontology.model.pprm.RecordDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordDocumentRepository extends JpaRepository<RecordDocument,String> {
    List<RecordDocument> findByRecordNo(String recordNo);
}

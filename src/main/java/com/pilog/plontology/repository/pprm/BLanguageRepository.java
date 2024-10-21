package com.pilog.plontology.repository.pprm;



import com.pilog.plontology.model.pprm.BLanguage;
import com.pilog.plontology.model.pprm.BLanguageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BLanguageRepository extends JpaRepository<BLanguage, BLanguageId> {

    @Query("SELECT b.id.languageId, b.id.name FROM BLanguage b WHERE b.id.languageId IN :languageId")
    List<BLanguage> findByLanguageIds(@Param("languageId") String languageId);
}

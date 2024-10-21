package com.pilog.plontology.repository.pprm;

import com.pilog.plontology.model.pprm.MvSmartSearchTextRef;
import com.pilog.plontology.repository.pprm.custom.MvSmartSearchTextRefRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MvSmartSearchTextRefRepository extends JpaRepository<MvSmartSearchTextRef, String> , MvSmartSearchTextRefRepositoryCustom {
}








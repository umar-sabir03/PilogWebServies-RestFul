package com.pilog.plontology.repository.pprm;

import com.pilog.plontology.model.pprm.OrgnStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrgnStructureRepo extends JpaRepository<OrgnStructure,String> {
    @Query("SELECT DISTINCT a.orgnId FROM OrgnStructure a WHERE a.orgnId = :orgnId")
    List<String> getByOrgId(String orgnId);
}

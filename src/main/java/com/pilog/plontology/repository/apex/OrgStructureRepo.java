package com.pilog.plontology.repository.apex;

import com.pilog.plontology.model.apex.OrgStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrgStructureRepo extends JpaRepository<OrgStructure,String> {
    @Query("SELECT DISTINCT a.orgId FROM OrgStructure a WHERE a.orgId = :orgnId")
    List<String> getByOrgId(String orgnId);
}

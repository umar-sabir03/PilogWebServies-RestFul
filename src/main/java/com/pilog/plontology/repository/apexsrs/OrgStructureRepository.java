package com.pilog.plontology.repository.apexsrs;

import com.pilog.plontology.model.apexsrs.OrgStructure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgStructureRepository extends JpaRepository<OrgStructure,String> {
    OrgStructure  findByNameIgnoreCase(String name);
}

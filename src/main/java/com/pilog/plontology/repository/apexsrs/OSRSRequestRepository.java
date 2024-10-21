package com.pilog.plontology.repository.apexsrs;

import com.pilog.plontology.model.apexsrs.OSRSRequests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OSRSRequestRepository extends JpaRepository<OSRSRequests,String> {
}

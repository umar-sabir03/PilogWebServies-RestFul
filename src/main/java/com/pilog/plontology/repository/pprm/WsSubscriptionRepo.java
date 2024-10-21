package com.pilog.plontology.repository.pprm;

import com.pilog.plontology.model.pprm.WsSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WsSubscriptionRepo extends JpaRepository<WsSubscription,String> {


    Optional<WsSubscription> findByOrgnIdAndWsIdIn(String orgnId, List<String> auditId);
}

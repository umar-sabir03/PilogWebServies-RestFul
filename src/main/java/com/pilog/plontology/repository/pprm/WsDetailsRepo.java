package com.pilog.plontology.repository.pprm;

import com.pilog.plontology.model.pprm.WsDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WsDetailsRepo extends JpaRepository<WsDetails,String> {
     List<WsDetails> findByWsName(String wsName);
}

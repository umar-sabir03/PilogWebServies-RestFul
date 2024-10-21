package com.pilog.plontology.access;

import com.pilog.plontology.model.pprm.WsDetails;
import com.pilog.plontology.model.pprm.WsHistory;
import com.pilog.plontology.model.pprm.WsSubscription;
import com.pilog.plontology.repository.apex.OrgStructureRepo;
import com.pilog.plontology.repository.pprm.OrgnStructureRepo;
import com.pilog.plontology.repository.pprm.WsDetailsRepo;
import com.pilog.plontology.repository.pprm.WsHistoryRepo;
import com.pilog.plontology.repository.pprm.WsSubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Component
public class WSAccessHistory {

    @PersistenceContext
    @Qualifier("pprmEntityManager")
    private EntityManager pprmEntityManager;

    @Autowired
    private OrgStructureRepo orgStructureRepo;
    @Autowired
    private WsHistoryRepo wsHistoryRepo;
    @Autowired
    private WsDetailsRepo wsDetailsRepo;
    @Autowired
    private OrgnStructureRepo orgnStructureRepo;
    @Autowired
    private WsSubscriptionRepo wsSubscriptionRepo;

    private static final Logger logger = Logger.getLogger(WSAccessHistory.class.getName());

    private static WSAccessHistory instance = null;

    public static WSAccessHistory getInstance() {
        if (instance == null) {
            instance = new WSAccessHistory();
        }
        return instance;
    }

    public synchronized String validateSubscription(String orgn_id, String ws_name) {
        logger.info("Validating subscription for orgn_id: " + orgn_id + ", ws_name: " + ws_name);
        int returncode = 0;

        if (!isValidRaw(orgn_id)) {
            returncode = 4;
        } else {
            Integer hits = 0;
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date currentDate = new java.sql.Date(utilDate.getTime());
            try {
                List<WsDetails> byWsName = wsDetailsRepo.findByWsName(ws_name);
                List<String> wsAuditIdList = byWsName.stream().map(WsDetails::getWsId).collect(Collectors.toList());
                List<WsHistory> wsHistoryList = wsHistoryRepo.getNumberOfHitsPerDay(orgn_id, currentDate, wsAuditIdList);
                if (!wsHistoryList.isEmpty()) {
                    hits = wsHistoryList.get(0).getNoOfHitsPerDay();
                }

                Optional<WsSubscription> subscriptionOptional = wsSubscriptionRepo.findByOrgnIdAndWsIdIn(orgn_id, wsAuditIdList);
                if (subscriptionOptional.isPresent()) {
                    WsSubscription subscription = subscriptionOptional.get();
                    Date subscriptionExpDate = subscription.getSubscriptionExpiryDate();
                    Integer maxHits = subscription.getMaxLimit();
                    Date renewalDate = subscription.getRenewalDate();
                    if (currentDate.compareTo(subscriptionExpDate) > 0) {
                        returncode = 2; // Expiration Date Reached
                    } else if (hits.compareTo(maxHits) > 0) {
                        returncode = 3; // Maximum Hits Reached
                    }
                } else {
                    returncode = 1; // User is not subscribed for the web service
                }
            } catch (Exception e) {
                logger.severe("Error in validateSubscription");
            }
        }
        String message="";
        switch (returncode) {
        case 1:
            message = "You are not subscribed for Webservice. Please contact admin with request info.";
            break;
        case 2:
            message = "You reached the expiration date. Please renew the subscription.";
            break;
        case 3:
            message = "You reached Maximum number of hits of the day. Please try tomorrow.";
            break;
        case 4:
            message = "Invalid Organization ID";
            break;
        default:
            message = "";
    }

    return message;
    }

    public boolean isValidRaw(String orgn_id) {
        logger.info("Checking validity of orgn_id: " + orgn_id);

        boolean valid = false;
        if (orgn_id.matches("[A-Z0-9]{32}")) {
            logger.info("Orgn_id is valid hex");
            valid = isApex2POrganization(orgn_id) || isPprmOrganization(orgn_id);
        }

        logger.info("Checking availability of given orgn Id: " + orgn_id + ", valid: " + valid);
        return valid;
    }

    public boolean isPprmOrganization(String orgn_id) {
        try {
            List<String> orgIdList = orgnStructureRepo.getByOrgId(orgn_id);
            boolean result = !orgIdList.isEmpty();
            logger.info("isPprmOrganization result for orgn_id: " + orgn_id + " - " + result);
            return result;
        } catch (Exception e) {
            logger.severe("Error in isPprmOrganization");
            return false;
        }
    }

    public boolean isApex2POrganization(String orgn_id) {
        try {
            List<String> orgIdList = orgStructureRepo.getByOrgId(orgn_id);
            boolean result = !orgIdList.isEmpty();
            logger.info("isApex2POrganization result for orgn_id: " + orgn_id + " - " + result);
            return result;
        } catch (Exception e) {
            logger.severe("Error in isApex2POrganization");
            return false;
        }
    }
}

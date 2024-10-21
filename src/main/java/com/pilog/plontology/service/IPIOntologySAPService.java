package com.pilog.plontology.service;

import com.pilog.plontology.payloads.*;

import java.util.List;

public interface IPIOntologySAPService {
    List<MTRLPPOTermDetail> getClassDetailData(ClassRequestDetailInfo request);

    List<MTRLPPOTermDetail> getClassSAPData(ClassRequestSAPDetailInfo request);

    List<MTRLPPOTermSimple> getClassSimpleData(ClassRequestDetailInfo request);

    List<MTRLPPOTERMAdvanced> getClassAdvanceData(ClassRequesAdvancedInfo request);
}

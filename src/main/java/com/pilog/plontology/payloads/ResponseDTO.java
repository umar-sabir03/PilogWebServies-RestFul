package com.pilog.plontology.payloads;
import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO {

    private List<DataResponse> masterDataList ;

    private List<DataResponse> documentDataList ;

    private List<DataResponse> textDataList ;

    private List<DataResponse> referenceDataList ;

    private List<DataResponse> characteristicsDataList ;

}

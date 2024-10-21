package com.pilog.plontology.payloads;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SAPCharacteristicsERP {

    @JsonProperty("ATINN")
    private String atinn;

    @JsonProperty("ATWRT")
    private String atwrt;

    @JsonProperty("ATZHL")
    private String atzhl;

    @JsonProperty("DISPLAY_ORDER")
    private String displayOrder;

    @JsonProperty("CHAR_VALUE")
    private String charValue;

    @JsonProperty("CHAR_VALUE_TXT")
    private String charValueTxt;

    @JsonProperty("NUMC_VALUE_FROM")
    private String numcValueFrom;

    @JsonProperty("UNIT_FROM")
    private String unitFrom;

    @JsonProperty("NUMC_VALUE_TO")
    private String numcValueTo;

    @JsonProperty("UNIT_TO")
    private String unitTo;

    @JsonProperty("CURR_VALUE_FROM")
    private String currValueFrom;

    @JsonProperty("CURR_VALUE_TO")
    private String currValueTo;

    @JsonProperty("CURR")
    private String curr;

    @JsonProperty("DATE_VALUE_FROM")
    private String dateValueFrom;

    @JsonProperty("DATE_VALUE_TO")
    private String dateValueTo;

    @JsonProperty("TIME_VALUE_FROM")
    private String timeValueFrom;

    @JsonProperty("TIME_VALUE_TO")
    private String timeValueTo;

    @JsonProperty("INTERVAL_TYPE")
    private String intervalType;

    @JsonProperty("INCONSISTENT")
    private String inconsistent;

    @JsonProperty("DEFAULT_VALUE")
    private String defaultValue;

    @JsonProperty("VALUE_SET_BY")
    private String valueSetBy;

    @JsonProperty("LTXT_ATINN")
    private String ltxtAtinn;

    @JsonProperty("LTXT_ID")
    private String ltxtId;

    @JsonProperty("DOKNR")
    private String doknr;

    @JsonProperty("DOKAR")
    private String dokar;

    @JsonProperty("DOKTL")
    private String doktl;

    @JsonProperty("DOKVR")
    private String dokvr;

    @JsonProperty("VALUE_ABBR")
    private String valueAbbr;

}

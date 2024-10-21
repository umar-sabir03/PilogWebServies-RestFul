package com.pilog.plontology.service;

import com.pilog.plontology.payloads.DataRequirementDataRow;
import com.pilog.plontology.payloads.PreferenceOntologyDataRow;

import java.util.List;

public interface IT8DictionaryTransferService {
    List<PreferenceOntologyDataRow> getPreferenceOntology(String orgID, String instance, String ssUsername);

    List<PreferenceOntologyDataRow> getPreferenceOntologyWithLanguageID(String orgID, String languageID, String instance, String ssUsername);

    List<DataRequirementDataRow> getDataRequirementWithLanguageID(String orgID, String languageID, String instance, String ssUsername);

    String getDataRequirementWithLanguageIDCount(String orgID, String languageID, String instance, String ssUsername);

    String getUoMWithLanguageIDCount(String orgID, String languageID, String instance, String ssUsername);

    String getTranslationsCount(String orgID, String languageID, String instance, String ssUsername);

    String getDataRequirementWithLanguageIDCountforInsert(String orgID, String languageID, String instance, String ssUsername);

    String getDataRequirementWithLanguageIDCountforDelete(String orgID, String languageID, String instance, String ssUsername);
}

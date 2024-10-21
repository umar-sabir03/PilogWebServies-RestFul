package com.pilog.plontology.service;

import com.pilog.plontology.model.pprm.BLanguage;
import com.pilog.plontology.model.pprm.DrCharValues;
import com.pilog.plontology.model.pprm.DrCharValuesId;
import com.pilog.plontology.payloads.RefreshCharValuesRequest;
import com.pilog.plontology.payloads.RefreshCharValuesRequestDTO;
import com.pilog.plontology.repository.pprm.BLanguageRepository;
import com.pilog.plontology.repository.pprm.DrCharValuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RefreshCharSearchImpl implements IRefreshCharSearch{

    @Autowired
    private DrCharValuesRepository drCharValuesRepository;
    @Autowired
    private BLanguageRepository bLanguageRepository;
    @Override
    @Transactional
    public void refreshCharValues(RefreshCharValuesRequest charValuesRequest) {

        if (charValuesRequest != null) {
            String orgnId = charValuesRequest.getOrgnId();
            List<RefreshCharValuesRequestDTO> charValuesRequestDTOs = charValuesRequest.getCharValuesRequestDTOs();
            if (charValuesRequestDTOs != null && !charValuesRequestDTOs.isEmpty()) {
                String languageIds = charValuesRequestDTOs.stream()
                        .filter(charValuesRequestDTO -> (charValuesRequestDTO.getLanguageId() != null
                                && !"".equalsIgnoreCase(charValuesRequestDTO.getLanguageId())
                                && !"null".equalsIgnoreCase(charValuesRequestDTO.getLanguageId())))
                        .map(charValuesRequestDTO -> (charValuesRequestDTO.getLanguageId()))
                        .distinct().collect(Collectors.joining("','"));
                List<BLanguage> languages = getLanguages(languageIds);
                String lastvalueConceptIdSeq = getLastValueConceptId();
                long lastSeq = 0;
                if (lastvalueConceptIdSeq != null
                        && !"".equalsIgnoreCase(lastvalueConceptIdSeq)
                        && !"null".equalsIgnoreCase(lastvalueConceptIdSeq)
                        && isNumeric(lastvalueConceptIdSeq)
                ) {
                    lastSeq = Long.parseLong(lastvalueConceptIdSeq);
                }

                    for (RefreshCharValuesRequestDTO charValuesRequestDTO : charValuesRequestDTOs) {
                        if (charValuesRequestDTO != null ) {
                            if (checkValueCombination(charValuesRequestDTO)) {
                                try {//1007-1#07-2726657#1
                                    lastSeq++;

                                    String valueConceptId = "1007-1#07-" + lastSeq + "#1";
                                    DrCharValues drCharValue = new DrCharValues();
                                    DrCharValuesId drCharValuesId=new DrCharValuesId();
                                    drCharValuesId.setClassConceptId(charValuesRequestDTO.getClassConceptId());
                                    drCharValuesId.setPropertyConceptId(charValuesRequestDTO.getPropertyConceptId());
                                    drCharValuesId.setValueConceptId(valueConceptId);
                                    drCharValuesId.setTerm(charValuesRequestDTO.getPropertyValue1());
                                    drCharValuesId.setLanguageId(charValuesRequestDTO.getLanguageId());
                                    drCharValue.setId(drCharValuesId);
                                    drCharValue.setAbbreviationId("");
                                    drCharValue.setAbbreviation(charValuesRequestDTO.getPropertyAbbr());
                                    BLanguage selectedLanguage = languages.stream()
                                            .filter(lang -> lang.getId().getLanguageId().equals(charValuesRequestDTO.getLanguageId()))
                                            .findFirst()
                                            .orElse(null);
                                    drCharValue.setLanguage(selectedLanguage.getId().getName());
                                    drCharValue.setDataType(charValuesRequestDTO.getDataType());
                                    drCharValue.setCreateBy("Schedular");
                                    drCharValue.setEditBy("Schedular");
                                    drCharValue.setDrId("594BD9AD235931C7E053460AA9C0A0AC");
                                    drCharValue.setTerm2("");
                                    drCharValue.setActiveInd("N");

                                    drCharValuesRepository.save(drCharValue);
                                } catch (Exception e) {
                                    continue;
                                }
                            }
                        }
                   }
            }
        }
    }
    @Transactional(readOnly = true)
    public List<BLanguage> getLanguages(String languageId) {
        List<BLanguage> languages = bLanguageRepository.findByLanguageIds(languageId);
        return languages;
    }

    @Transactional(readOnly = true)
    public String getLastValueConceptId() {
        String lastSeq = "";

        Optional<String> valueConceptIdOpt = drCharValuesRepository.findFirstByOrderByIdValueConceptIdDesc();

        if (valueConceptIdOpt.isPresent()) {
            String valueConceptId = valueConceptIdOpt.get();
            if (valueConceptId != null && !valueConceptId.isEmpty()) {
                valueConceptId = valueConceptId.replace("1007-1#07-", "");
                lastSeq = valueConceptId.replace("#1", "");
            }
        }

        return lastSeq;
    }
    @Transactional(readOnly = true)
    public boolean checkValueCombination(RefreshCharValuesRequestDTO charValuesRequestDTO) {
        long count = drCharValuesRepository.countByValues(
                charValuesRequestDTO.getClassConceptId(),
                charValuesRequestDTO.getPropertyConceptId(),
                charValuesRequestDTO.getPropertyName(),
                charValuesRequestDTO.getLanguageId()
        );

        if (count == 0) {
            return true;
        }

        return false;
    }
    public boolean isNumeric(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

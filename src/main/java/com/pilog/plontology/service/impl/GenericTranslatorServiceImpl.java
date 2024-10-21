package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.V10TranslateRequest;
import com.pilog.plontology.payloads.V10TranslateResponse;
import com.pilog.plontology.payloads.V10TranslateResponseDTO;
import com.pilog.plontology.service.IGenericTranslatorService;
import com.pilog.plontology.service.IV10TranslatorBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GenericTranslatorServiceImpl implements IGenericTranslatorService {
    @Autowired
    private IV10TranslatorBeanService iv10TranslatorBeanService;
    @Override
    public V10TranslateResponse getGenericTranslator(V10TranslateRequest translateRequest) {
        V10TranslateResponse translateResponse = new V10TranslateResponse();
        try {
            Map<String, Object> dbTranslations = iv10TranslatorBeanService.checkTranslations(translateRequest);
            if (dbTranslations != null && !dbTranslations.isEmpty()) {
                Map<String, String> translationsMap = (Map<String, String>) dbTranslations.get("translationsMap");
                List translationsList = (List) dbTranslations.get("translationsList");
                if (translationsList != null && !translationsList.isEmpty()) {
                    translateRequest.setWordsList(translationsList);
                    translateResponse = iv10TranslatorBeanService.translate(translateRequest);

                    if (translateResponse != null) {
                        List<V10TranslateResponseDTO> translateResponseList = translateResponse.getTranslatedWords();
                        if (translationsMap != null && !translationsMap.isEmpty()) {
                            for (String term : translationsMap.keySet()) {
                                V10TranslateResponseDTO responseDTO = new V10TranslateResponseDTO();
                                responseDTO.setWord(term);
                                responseDTO.setTranslatedWord(translationsMap.get(term));
                                translateResponseList.add(responseDTO);
                            }
                            translateResponse.setTranslatedWords(translateResponseList);
                        }
                    } else {
                        List<V10TranslateResponseDTO> translateResponseList = new ArrayList<>();
                        if (translationsMap != null && !translationsMap.isEmpty()) {
                            for (String term : translationsMap.keySet()) {
                                V10TranslateResponseDTO responseDTO = new V10TranslateResponseDTO();
                                responseDTO.setWord(term);
                                responseDTO.setTranslatedWord(translationsMap.get(term));
                                translateResponseList.add(responseDTO);
                            }
                            translateResponse.setTranslatedWords(translateResponseList);
                        }
                    }
                }else{
                    List<V10TranslateResponseDTO> translateResponseList = new ArrayList<>();
                    if (translationsMap != null && !translationsMap.isEmpty()) {
                        for (String term : translationsMap.keySet()) {
                            V10TranslateResponseDTO responseDTO = new V10TranslateResponseDTO();
                            responseDTO.setWord(term);
                            responseDTO.setTranslatedWord(translationsMap.get(term));
                            translateResponseList.add(responseDTO);
                        }
                        translateResponse.setTranslatedWords(translateResponseList);
                    }
                }

            } else {
                translateResponse = iv10TranslatorBeanService.translate(translateRequest);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return translateResponse;
    }
}

package com.pilog.plontology.service.impl;

import com.pilog.plontology.payloads.TranslationRequest;
import com.pilog.plontology.service.ILocaleProcessService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LocaleProcessServiceImpl implements ILocaleProcessService {
    @PersistenceContext(unitName = "apexEntityManagerFactory")
    private EntityManager entityManager;
    @Value("google.api.key")
    private String googleApiKey;
    @Override
    public String doTranslate(TranslationRequest request) {
        String oTerm = "";
        try {
            String oLocale = request.getOlocale() != null ? request.getOlocale() : "";
            String term = request.getTerm() != null ? request.getTerm() : "";

            // First query as a native SQL query
            String query1 = "select b.term from org_terminology a,org_terminology b,language c " +
                    "WHERE a.org_Id = b.org_Id " +
                    "AND a.org_Id = 'E6EE49F8383C494098B18D06C64DDFF0' " +
                    "AND a.language_Id = 'CC0092ADF415443DB36744B5D9EF96E9' " +
                    "AND c.language_Code = ? " +
                    "AND UPPER(a.term) = UPPER(?) " +
                    "AND b.language_Id = c.language_Id " +
                    "AND a.concept_Id = b.concept_Id";
            Query nativeQuery1 = entityManager.createNativeQuery(query1);
            nativeQuery1.setParameter(1, oLocale);
            nativeQuery1.setParameter(2, term);
            List<String> results = nativeQuery1.getResultList();
            oTerm = results.isEmpty() ? "" : results.get(0);
            if (oTerm.isEmpty()) {
                String query2 = "SELECT b.term FROM IEV A, IEV B " +
                        "WHERE A.ievRef = B.ievRef " +
                        "AND A.language = 'en' " +
                        "AND b.language = ? " +
                        "AND UPPER(a.term) = UPPER(?)";
                Query nativeQuery2 = entityManager.createNativeQuery(query2);
                nativeQuery2.setParameter(1, oLocale);
                nativeQuery2.setParameter(2, term);
                List<String> results2 = nativeQuery2.getResultList();
                oTerm = results.isEmpty() ? "" : results2.get(0);

                if (oTerm.isEmpty()) {
                    oTerm = translateFromGoogle(term, "en", oLocale);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }

        return oTerm;
    }
    private String translateFromGoogle(String text, String source, String target) {
        String response = "";
        try {
            RestTemplate restTemplate = new RestTemplate();
            String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
            String url = String.format("https://www.googleapis.com/language/translate/v2?key=%s&q=%s&source=%s&target=%s",
                    System.getenv(googleApiKey), encodedText, source, target);

            response = restTemplate.getForObject(url, String.class);

            int startIndex = response.indexOf("\"translatedText\":\"") + "\"translatedText\":\"".length();
            int endIndex = response.indexOf("\"", startIndex);

            if (startIndex > -1 && endIndex > startIndex) {
                response = response.substring(startIndex, endIndex);
            } else {
                response = "Translation not found.";
            }
        } catch (UnsupportedEncodingException e) {
            return "Encoding error occurred.";
        } catch (Exception e) {
            return "An unexpected error occurred.";
        }
        return response;
    }


}

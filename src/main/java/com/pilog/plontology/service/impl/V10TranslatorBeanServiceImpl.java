package com.pilog.plontology.service.impl;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;
import com.pilog.plontology.payloads.V10TranslateRequest;
import com.pilog.plontology.payloads.V10TranslateResponse;
import com.pilog.plontology.payloads.V10TranslateResponseDTO;
import com.pilog.plontology.repository.apex.V10TranslatorBeanRepo;
import com.pilog.plontology.service.IV10TranslatorBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class V10TranslatorBeanServiceImpl implements IV10TranslatorBeanService {
    @Autowired
    private V10TranslatorBeanRepo v10TranslatorBeanRepo;
    @Value("google.api.key")
    private String googleApiKey;

    public static Map<String,String> htmlEntityMap = new HashMap<>();
    @Override
    public Map<String, Object> checkTranslations(V10TranslateRequest translateRequest) {
        Map<String, Object> translationsResultMap = new HashMap<>();
        try {

            List<String> totalValuesList = new ArrayList<>(translateRequest.getWordsList());
            System.out.println("totalValuesList:::" + totalValuesList);
            Map<String, String> translationsMap = new HashMap<>();
            List<Object[]> resultsPPO = v10TranslatorBeanRepo.getPPOMlValuesList(translateRequest.getTarget(), totalValuesList);
            for (Object[] result : resultsPPO) {
                String term = (String) result[0];
                String transTerm = (String) result[1];
                if (transTerm != null && !transTerm.isEmpty() && !"null".equalsIgnoreCase(transTerm)) {
                    translationsMap.put(term, transTerm);
                    totalValuesList.remove(term);
                }
            }

            System.out.println("translationsMap:PPO::" + translationsMap);
            System.out.println("totalValuesList:PPO::" + totalValuesList);

            if (!totalValuesList.isEmpty()) {
                     List<Object[]> resultsPRO = v10TranslatorBeanRepo.getPROMlValuesList(translateRequest.getTarget(), totalValuesList);
                for (Object[] result : resultsPRO) {
                    String term = (String) result[0];
                    String transTerm = (String) result[1];
                    if (transTerm != null && !transTerm.isEmpty() && !"null".equalsIgnoreCase(transTerm)) {
                        translationsMap.put(term, transTerm);
                        totalValuesList.remove(term);
                    }
                }
            }

            System.out.println("translationsMap:PRO::" + translationsMap);
            System.out.println("totalValuesList:PRO::" + totalValuesList);

            if (!totalValuesList.isEmpty()) {
              List<Object[]> resultsIEV = v10TranslatorBeanRepo.getIEVValuesMl(translateRequest.getTarget().split("_")[0], totalValuesList);
                for (Object[] result : resultsIEV) {
                    String term = (String) result[0];
                    String transTerm = (String) result[1];
                    if (transTerm != null && !transTerm.isEmpty() && !"null".equalsIgnoreCase(transTerm)) {
                        translationsMap.put(term, transTerm);
                        totalValuesList.remove(term);
                    }
                }
            }

            System.out.println("translationsMap:IEV::" + translationsMap);
            System.out.println("totalValuesList:IEV::" + totalValuesList);

            translationsResultMap.put("translationsMap", translationsMap);
            translationsResultMap.put("translationsList", totalValuesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return translationsResultMap;
    }

    @Override
    public V10TranslateResponse translate(V10TranslateRequest translateRequest) {
        V10TranslateResponse translateResponse = new V10TranslateResponse();
        try {
            htmlEntityMap.put("&amp;", "&");
            htmlEntityMap.put("&apos;", "'");
            htmlEntityMap.put("&lt;", "<");
            htmlEntityMap.put("&gt;", ">");
            htmlEntityMap.put("&nbsp;", " ");
            htmlEntityMap.put("&iexcl;", "¡");
            htmlEntityMap.put("&cent;", "¢");
            htmlEntityMap.put("&pound;", "£");
            htmlEntityMap.put("&curren;", "¤");
            htmlEntityMap.put("&yen;", "¥");
            htmlEntityMap.put("&brvbar;", "¦");
            htmlEntityMap.put("&sect;", "§");
            htmlEntityMap.put("&uml;", "¨");
            htmlEntityMap.put("&copy;", "©");
            htmlEntityMap.put("&ordf;", "ª");
            htmlEntityMap.put("&laquo;", "«");
            htmlEntityMap.put("&not;", "¬");
            htmlEntityMap.put("&reg;", "®");
            htmlEntityMap.put("&macr;", "¯");
            htmlEntityMap.put("&deg;", "°");
            htmlEntityMap.put("&plusmn;", "±");
            htmlEntityMap.put("&sup2;", "²");
            htmlEntityMap.put("&sup3;", "³");
            htmlEntityMap.put("&acute;", "´");
            htmlEntityMap.put("&micro;", "µ");
            htmlEntityMap.put("&para;", "¶");
            htmlEntityMap.put("&middot;", "·");
            htmlEntityMap.put("&cedil;", "¸");
            htmlEntityMap.put("&sup1;", "¹");
            htmlEntityMap.put("&ordm;", "º");
            htmlEntityMap.put("&raquo;", "»");
            htmlEntityMap.put("&frac14;", "¼");
            htmlEntityMap.put("&frac12;", "½");
            htmlEntityMap.put("&frac34;", "¾");
            htmlEntityMap.put("&iquest;", "¿");
            htmlEntityMap.put("&Agrave;", "À");
            htmlEntityMap.put("&Aacute;", "Á");
            htmlEntityMap.put("&Acirc;", "Â");
            htmlEntityMap.put("&Atilde;", "Ã");
            htmlEntityMap.put("&Auml;", "Ä");
            htmlEntityMap.put("&Aring;", "Å");
            htmlEntityMap.put("&AElig;", "Æ");
            htmlEntityMap.put("&Ccedil;", "Ç");
            htmlEntityMap.put("&Egrave;", "È");
            htmlEntityMap.put("&Eacute;", "É");
            htmlEntityMap.put("&Ecirc;", "Ê");
            htmlEntityMap.put("&Euml;", "Ë");
            htmlEntityMap.put("&Igrave;", "Ì");
            htmlEntityMap.put("&Iacute;", "Í");
            htmlEntityMap.put("&Icirc;", "Î");
            htmlEntityMap.put("&Iuml;", "Ï");
            htmlEntityMap.put("&ETH;", "Ð");
            htmlEntityMap.put("&Ntilde;", "Ñ");
            htmlEntityMap.put("&Ograve;", "Ò");
            htmlEntityMap.put("&Oacute;", "Ó");
            htmlEntityMap.put("&Ocirc;", "Ô");
            htmlEntityMap.put("&Otilde;", "Õ");
            htmlEntityMap.put("&Ouml;", "Ö");
            htmlEntityMap.put("&times;", "×");
            htmlEntityMap.put("&Oslash;", "Ø");
            htmlEntityMap.put("&Ugrave;", "Ù");
            htmlEntityMap.put("&Uacute;", "Ú");
            htmlEntityMap.put("&Ucirc;", "Û");
            htmlEntityMap.put("&Uuml;", "Ü");
            htmlEntityMap.put("&Yacute;", "Ý");
            htmlEntityMap.put("&THORN;", "Þ");
            htmlEntityMap.put("&szlig;", "ß");
            htmlEntityMap.put("&agrave;", "à");
            htmlEntityMap.put("&aacute;", "á");
            htmlEntityMap.put("&acirc;", "â");
            htmlEntityMap.put("&atilde;", "ã");
            htmlEntityMap.put("&auml;", "ä");
            htmlEntityMap.put("&aring;", "å");
            htmlEntityMap.put("&aelig;", "æ");
            htmlEntityMap.put("&ccedil;", "ç");
            htmlEntityMap.put("&egrave;", "è");
            htmlEntityMap.put("&eacute;", "é");
            htmlEntityMap.put("&ecirc;", "ê");
            htmlEntityMap.put("&euml;", "ë");
            htmlEntityMap.put("&igrave;", "ì");
            htmlEntityMap.put("&iacute;", "í");
            htmlEntityMap.put("&icirc;", "î");
            htmlEntityMap.put("&iuml;", "ï");
            htmlEntityMap.put("&eth;", "ð");
            htmlEntityMap.put("&ntilde;", "ñ");
            htmlEntityMap.put("&ograve;", "ò");
            htmlEntityMap.put("&oacute;", "ó");
            htmlEntityMap.put("&ocirc;", "ô");
            htmlEntityMap.put("&otilde;", "õ");
            htmlEntityMap.put("&ouml;", "ö");
            htmlEntityMap.put("&divide;", "÷");
            htmlEntityMap.put("&oslash;", "ø");
            htmlEntityMap.put("&ugrave;", "ù");
            htmlEntityMap.put("&uacute;", "ú");
            htmlEntityMap.put("&ucirc;", "û");
            htmlEntityMap.put("&uuml;", "ü");
            htmlEntityMap.put("&yacute;", "ý");
            htmlEntityMap.put("&thorn;", "þ");
            htmlEntityMap.put("&yuml;", "ÿ");
            htmlEntityMap.put("&OElig;", "Œ");
            htmlEntityMap.put("&oelig;", "œ");
            htmlEntityMap.put("&Scaron;", "Š");
            htmlEntityMap.put("&scaron;", "š");
            htmlEntityMap.put("&Yuml;", "Ÿ");
            htmlEntityMap.put("&fnof;", "ƒ");
            htmlEntityMap.put("&circ;", "ˆ");
            htmlEntityMap.put("&tilde;", "˜");
            htmlEntityMap.put("&Alpha;", "Α");
            htmlEntityMap.put("&Beta;", "Β");
            htmlEntityMap.put("&Gamma;", "Γ");
            htmlEntityMap.put("&Delta;", "Δ");
            htmlEntityMap.put("&Epsilon;", "Ε");
            htmlEntityMap.put("&Zeta;", "Ζ");
            htmlEntityMap.put("&Eta;", "Η");
            htmlEntityMap.put("&Theta;", "Θ");
            htmlEntityMap.put("&Iota;", "Ι");
            htmlEntityMap.put("&Kappa;", "Κ");
            htmlEntityMap.put("&Lambda;", "Λ");
            htmlEntityMap.put("&Mu;", "Μ");
            htmlEntityMap.put("&Nu;", "Ν");
            htmlEntityMap.put("&Xi;", "Ξ");
            htmlEntityMap.put("&Omicron;", "Ο");
            htmlEntityMap.put("&Pi;", "Π");
            htmlEntityMap.put("&Rho;", "Ρ");
            htmlEntityMap.put("&Sigma;", "Σ");
            htmlEntityMap.put("&Tau;", "Τ");
            htmlEntityMap.put("&Upsilon;", "Υ");
            htmlEntityMap.put("&Phi;", "Φ");
            htmlEntityMap.put("&Chi;", "Χ");
            htmlEntityMap.put("&Psi;", "Ψ");
            htmlEntityMap.put("&Omega;", "Ω");
            htmlEntityMap.put("&alpha;", "α");
            htmlEntityMap.put("&beta;", "β");
            htmlEntityMap.put("&gamma;", "γ");
            htmlEntityMap.put("&delta;", "δ");
            htmlEntityMap.put("&epsilon;", "ε");
            htmlEntityMap.put("&zeta;", "ζ");
            htmlEntityMap.put("&eta;", "η");
            htmlEntityMap.put("&theta;", "θ");
            htmlEntityMap.put("&iota;", "ι");
            htmlEntityMap.put("&kappa;", "κ");
            htmlEntityMap.put("&lambda;", "λ");
            htmlEntityMap.put("&mu;", "μ");
            htmlEntityMap.put("&nu;", "ν");
            htmlEntityMap.put("&xi;", "ξ");
            htmlEntityMap.put("&omicron;", "ο");
            htmlEntityMap.put("&pi;", "π");
            htmlEntityMap.put("&rho;", "ρ");
            htmlEntityMap.put("&sigmaf;", "ς");
            htmlEntityMap.put("&sigma;", "σ");
            htmlEntityMap.put("&tau;", "τ");
            htmlEntityMap.put("&upsilon;", "υ");
            htmlEntityMap.put("&phi;", "φ");
            htmlEntityMap.put("&chi;", "χ");
            htmlEntityMap.put("&psi;", "ψ");
            htmlEntityMap.put("&omega;", "ω");
            htmlEntityMap.put("&thetasym;", "ϑ");
            htmlEntityMap.put("&Upsih;", "ϒ");
            htmlEntityMap.put("&piv;", "ϖ");
            htmlEntityMap.put("&ndash;", "–");
            htmlEntityMap.put("&mdash;", "—");
            htmlEntityMap.put("&lsquo;", "‘");
            htmlEntityMap.put("&rsquo;", "’");
            htmlEntityMap.put("&sbquo;", "‚");
            htmlEntityMap.put("&ldquo;", "“");
            htmlEntityMap.put("&rdquo;", "”");
            htmlEntityMap.put("&bdquo;", "„");
            htmlEntityMap.put("&dagger;", "†");
            htmlEntityMap.put("&Dagger;", "‡");
            htmlEntityMap.put("&bull;", "•");
            htmlEntityMap.put("&hellip;", "…");
            htmlEntityMap.put("&permil;", "‰");
            htmlEntityMap.put("&prime;", "′");
            htmlEntityMap.put("&Prime;", "″");
            htmlEntityMap.put("&lsaquo;", "‹");
            htmlEntityMap.put("&rsaquo;", "›");
            htmlEntityMap.put("&oline;", "‾");
            htmlEntityMap.put("&frasl;", "⁄");
            htmlEntityMap.put("&euro;", "€");
            htmlEntityMap.put("&image;", "ℑ");
            htmlEntityMap.put("&weierp;", "℘");
            htmlEntityMap.put("&real;", "ℜ");
            htmlEntityMap.put("&trade;", "™");
            htmlEntityMap.put("&alefsym;", "ℵ");
            htmlEntityMap.put("&larr;", "←");
            htmlEntityMap.put("&uarr;", "↑");
            htmlEntityMap.put("&rarr;", "→");
            htmlEntityMap.put("&darr;", "↓");
            htmlEntityMap.put("&harr;", "↔");
            htmlEntityMap.put("&crarr;", "↵");
            htmlEntityMap.put("&lArr;", "⇐");
            htmlEntityMap.put("&UArr;", "⇑");
            htmlEntityMap.put("&rArr;", "⇒");
            htmlEntityMap.put("&dArr;", "⇓");
            htmlEntityMap.put("&hArr;", "⇔");
            htmlEntityMap.put("&forall;", "∀");
            htmlEntityMap.put("&part;", "∂");
            htmlEntityMap.put("&exist;", "∃");
            htmlEntityMap.put("&empty;", "∅");
            htmlEntityMap.put("&nabla;", "∇");
            htmlEntityMap.put("&isin;", "∈");
            htmlEntityMap.put("&notin;", "∉");
            htmlEntityMap.put("&ni;", "∋");
            htmlEntityMap.put("&prod;", "∏");
            htmlEntityMap.put("&sum;", "∑");
            htmlEntityMap.put("&minus;", "−");
            htmlEntityMap.put("&lowast;", "∗");
            htmlEntityMap.put("&radic;", "√");
            htmlEntityMap.put("&prop;", "∝");
            htmlEntityMap.put("&infin;", "∞");
            htmlEntityMap.put("&ang;", "∠");
            htmlEntityMap.put("&and;", "∧");
            htmlEntityMap.put("&or;", "∨");
            htmlEntityMap.put("&cap;", "∩");
            htmlEntityMap.put("&cup;", "∪");
            htmlEntityMap.put("&int;", "∫");
            htmlEntityMap.put("&there4;", "∴");
            htmlEntityMap.put("&sim;", "∼");
            htmlEntityMap.put("&cong;", "≅");
            htmlEntityMap.put("&asymp;", "≈");
            htmlEntityMap.put("&ne;", "≠");
            htmlEntityMap.put("&equiv;", "≡");
            htmlEntityMap.put("&le;", "≤");
            htmlEntityMap.put("&ge;", "≥");
            htmlEntityMap.put("&sub;", "⊂");
            htmlEntityMap.put("&sup;", "⊃");
            htmlEntityMap.put("&nsub;", "⊄");
            htmlEntityMap.put("&sube;", "⊆");
            htmlEntityMap.put("&supe;", "⊇");
            htmlEntityMap.put("&oplus;", "⊕");
            htmlEntityMap.put("&otimes;", "⊗");
            htmlEntityMap.put("&perp;", "⊥");
            htmlEntityMap.put("&sdot;", "⋅");
            htmlEntityMap.put("&lceil;", "⌈");
            htmlEntityMap.put("&rceil;", "⌉");
            htmlEntityMap.put("&lfloor;", "⌊");
            htmlEntityMap.put("&rfloor;", "⌋");
            htmlEntityMap.put("&lang;", "⟨");
            htmlEntityMap.put("&rang;", "⟩");
            htmlEntityMap.put("&loz;", "◊");
            htmlEntityMap.put("&spades;", "♠");
            htmlEntityMap.put("&clubs;", "♣");
            htmlEntityMap.put("&hearts;", "♥");
            htmlEntityMap.put("&diams;", "♦");
            if (translateRequest != null) {
                Map<String, String> dataObject = new HashMap<>();
                if (translateRequest.getWordsList() != null && !translateRequest.getWordsList().isEmpty()) {
                    int totalSize = translateRequest.getWordsList().size();

                    for (int i = 0; i < totalSize; i = i + 100) {
                        List dataList = translateRequest.getWordsList().subList(i, ((i + 100) < totalSize ? (i + 100) : totalSize));
                        if (dataList != null && !dataList.isEmpty()) {

                            if (translateRequest.getSource() != null && ("en".equalsIgnoreCase(translateRequest.getSource())
                                    || "en_US".equalsIgnoreCase(translateRequest.getSource())
                                    || "US".equalsIgnoreCase(translateRequest.getSource()))) {
                                String langTo = translateRequest.getTarget();
                                // have to convert from english to other lang
                                if (translateRequest.getTarget() != null && translateRequest.getTarget().contains("_")) {
                                    langTo = langTo.split("_")[0];
                                }
                                dataObject.putAll(translateToOther(langTo, dataList));
                            }
                        }

                    }

                } else if (translateRequest.getWord() != null && !"".equalsIgnoreCase(translateRequest.getWord())) {
                    if (translateRequest.getSource() != null && ("en".equalsIgnoreCase(translateRequest.getSource())
                            || "en_US".equalsIgnoreCase(translateRequest.getSource())
                            || "US".equalsIgnoreCase(translateRequest.getSource()))) {
                        String langTo = translateRequest.getTarget();
                        // have to convert from english to other lang
                        if (langTo != null && langTo.contains("_")) {
                            langTo = langTo.split("_")[0];
                        }
                        dataObject.putAll(translateToOther(langTo, translateRequest.getWord()));
                    }
                }
                if (dataObject != null && !dataObject.isEmpty()) {
                    List<V10TranslateResponseDTO> translatedList = new ArrayList();
                    for (String key : dataObject.keySet()) {
                        V10TranslateResponseDTO responseDTO = new V10TranslateResponseDTO();
                        responseDTO.setWord(key);
                        responseDTO.setTranslatedWord(dataObject.get(key));
                        translatedList.add(responseDTO);
                    }
                    translateResponse.setTranslatedWords(translatedList);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return translateResponse;
    }

    private Map<String,String> translateToOther(String langTo, Object fromWord) {
        Map<String,String> translatedMap = new HashMap<>();
        try {
            List wordsList = new ArrayList();
            if (fromWord instanceof List) {
                wordsList = (List) fromWord;
            } else if (fromWord instanceof String) {
                wordsList.add(fromWord);
            }
            Translate translate = new Translate.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(), null)
                    // Set your application name
                    .setApplicationName("abc")
                    //                   .setApplicationName("Stackoverflow-Example")
                    .build();

            Translate.Translations.List translationsList = translate.new Translations().list(
                    wordsList,
                    langTo);

            // TODO: Set your API-Key from https://console.developers.google.com/
            translationsList.setKey(googleApiKey);
            TranslationsListResponse translationsListResponse = translationsList.execute();
            int i = 0;
            for (TranslationsResource translationsResource : translationsListResponse.getTranslations()) {
                //htmlEntityMap
                String translatedText = translationsResource.getTranslatedText();
                translatedText = htmlEntityMap.entrySet()
                        .stream()
                        .reduce(translatedText,
                                (s, e) -> s.replace(e.getKey(), e.getValue()),
                                (s1, s2) -> null);
                translatedMap.put((String) wordsList.get(i), translatedText);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return translatedMap;
    }
}

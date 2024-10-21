package com.pilog.plontology.payloads;
import lombok.Data;

import java.util.List;

@Data
public class V10TranslateRequest {

    private List<String> wordsList;
    private String word;
    private String source;
    private String target;
}

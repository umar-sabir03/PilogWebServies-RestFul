package com.pilog.plontology.payloads;

import lombok.Data;

@Data
public class DictionaryRequestDTO {
        private String orgID;
        private String languageID;
        private String instance;
        private String ssUsername;
}

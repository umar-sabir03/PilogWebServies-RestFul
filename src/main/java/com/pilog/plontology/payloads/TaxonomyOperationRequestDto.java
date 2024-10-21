package com.pilog.plontology.payloads;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TaxonomyOperationRequestDto {
        public String projectionFields;
        @NotBlank(message = "ConceptTypeId is required")
        private String conceptTypeId;

        @NotBlank(message = "LanguageId is required")
        private String languageId;

        @NotBlank(message = "OrgId is required")
        private String orgnId;

        @NotBlank(message = "Term is required")
        private String term;
        private String termOriginRef;
        private String definition;
        private String definitionOriginRef;
        private String abbrevation;
        private String abbrevationOriginRef;
        private String label;
        private String labelOriginatorRef;
        private String languageIRD1;
        private String statusReason;
        private String statusRc;
        private String classId;
        private String domain;
        private String sysCreatedBy;
        private String drId;
        private String manFlag;
        private Integer propSeq;
        private String dataType ;
        private String propName;
        private String propIND;
        private GeneratePageBean generatePageBean;

}

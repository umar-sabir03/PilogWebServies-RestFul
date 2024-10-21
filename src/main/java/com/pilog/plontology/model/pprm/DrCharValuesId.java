package com.pilog.plontology.model.pprm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DrCharValuesId implements Serializable {

    @Column(name = "CLASS_CONCEPT_ID", length = 30, nullable = false)
    private String classConceptId;

    @Column(name = "PROPERTY_CONCEPT_ID", length = 30, nullable = false)
    private String propertyConceptId;

    @Column(name = "VALUE_CONCEPT_ID", length = 30, nullable = false)
    private String valueConceptId;

    @Column(name = "TERM", length = 4000, nullable = false)
    private String term;

    @Column(name = "LANGUAGE_ID", length = 30, nullable = false)
    private String languageId;
}

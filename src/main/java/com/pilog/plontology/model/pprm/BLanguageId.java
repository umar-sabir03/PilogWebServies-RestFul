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
public class BLanguageId implements Serializable {

    @Column(name = "LANGUAGE_ID", length = 30, nullable = false)
    private String languageId;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;
}

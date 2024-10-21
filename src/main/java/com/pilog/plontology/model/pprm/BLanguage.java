package com.pilog.plontology.model.pprm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "B_LANGUAGE")
public class BLanguage {

    @EmbeddedId
    private BLanguageId id;

    @Column(name = "COUNTRY_CODE", length = 2)
    private String countryCode;

    @Column(name = "LANGUAGE_CODE", length = 2)
    private String languageCode;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDate createDate;

    @Column(name = "CREATE_BY", length = 50, nullable = false)
    private String createBy;

    @Column(name = "EDIT_DATE")
    private LocalDate editDate;

    @Column(name = "EDIT_BY", length = 50)
    private String editBy;

    @Column(name = "ACTIVE_FLAG", length = 1, nullable = false)
    private String activeFlag;

    @Column(name = "ACTIVE_IND", length = 1)
    private String activeInd;

    @Column(name = "AUDIT_ID", length = 100, nullable = false)
    private String auditId;
}

package com.pilog.plontology.model.pprm;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "O_RECORD_TEXT")
@Data
public class RecordText {

    @Id
    @Column(name = "RECORD_NO", length = 20, nullable = false)
    private String recordNo;

    @Column(name = "REGION", length = 20, nullable = false)
    private String region;

    @Column(name = "LOCALE", length = 5, nullable = false)
    private String locale;

    @Column(name = "TYPE", length = 20, nullable = false)
    private String type;

    @Column(name = "TEXT", length = 4000, nullable = false)
    private String text;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDate createDate;

    @Column(name = "CREATE_BY", length = 50, nullable = false)
    private String createBy;

    @Column(name = "EDIT_DATE")
    private LocalDate editDate;

    @Column(name = "EDIT_BY", length = 50)
    private String editBy;

    @Column(name = "AUDIT_ID", length = 60)
    private String auditId;

    @Column(name = "BUSINESS_UNIT", length = 20, nullable = false)
    private String businessUnit;

    @Column(name = "INSTANCE", length = 5, nullable = false)
    private String instance;

}

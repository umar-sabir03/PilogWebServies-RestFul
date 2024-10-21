package com.pilog.plontology.model.apex;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "V_PPO_VALUES_ML_NEW")
@Data
public class VPPOValuesMlNew implements Serializable {

    @Id
    @Column(name = "ORG_ID", columnDefinition = "RAW", nullable = false)
    private String orgId;

    @Column(name = "TERM", length = 500, nullable = false)
    private String term;

    @Column(name = "TRANS_TERM", length = 500, nullable = false)
    private String transTerm;

    @Column(name = "LOCALE", length = 101, nullable = false)
    private String locale;

    @Column(name = "LANGUAGE_CDE", length = 290, nullable = false)
    private String languageCde;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

}
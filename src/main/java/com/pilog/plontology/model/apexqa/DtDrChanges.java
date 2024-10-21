package com.pilog.plontology.model.apexqa;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "DT_DR_CHANGES")
@Data
public class DtDrChanges implements Serializable {

    @Column(name = "ORG_ID", columnDefinition = "RAW")
    private String orgId;

    @Id
    @Column(name = "DR_ID", columnDefinition = "RAW")
    private String drId;

    @Column(name = "STATE", length = 20)
    private String state;

}

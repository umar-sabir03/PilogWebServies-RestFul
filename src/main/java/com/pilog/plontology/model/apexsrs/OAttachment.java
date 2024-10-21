package com.pilog.plontology.model.apexsrs;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "O_ATTACHMENTS")
@Data
public class OAttachment {
    @Id
    @GenericGenerator(name = "gen1", strategy ="com.pilog.plontology.model.apexsrs.idgenerator.MyGenerator")
    @GeneratedValue(generator = "gen1")
    @Column(name = "DOC_NO", length = 4000)
    private String docNo;

    @Column(name = "SRS_ID", length = 4000)
    private String srsId;

    @Column(name = "BLOB_URL", length = 4000)
    private String blobUrl;

    @Column(name = "FILE_NAME", length = 4000)
    private String fileName;

    @Column(name = "DOC_TYPE", length = 300)
    private String docType;

    @Lob
    @Column(name = "BLOB_DATA")
    private byte[] blobData;

    @Column(name = "GROUP_ID", length = 50)
    private String groupId;

    @Column(name = "SESSION_ID", length = 50)
    private String sessionId;

    @Column(name = "USER_NAME", length = 50)
    private String userName;

    @Column(name = "ID")
    private Integer id;


}

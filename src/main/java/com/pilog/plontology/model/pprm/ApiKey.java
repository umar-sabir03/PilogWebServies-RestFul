package com.pilog.plontology.model.pprm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "API_KEY_PILOG_REPO_PROJECT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiKey {

    @Id
    private String apiKey;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "api_urls", joinColumns = @JoinColumn(name = "api_key"))
    @Column(name = "url")
    private Set<String> urls;

}

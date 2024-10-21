package com.pilog.plontology.service;

import com.pilog.plontology.payloads.OntologyRequest;
import com.pilog.plontology.payloads.OntologyResponse;

public interface ISearchOntology {
    OntologyResponse list(OntologyRequest request);
}

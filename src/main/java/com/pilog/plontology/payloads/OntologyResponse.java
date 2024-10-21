package com.pilog.plontology.payloads;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OntologyResponse implements Serializable
{
    public List<Ontology> classDataList;
}
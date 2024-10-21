package com.pilog.plontology.payloads;

import lombok.Data;

import java.util.List;

@Data
public class ReferenceResponse
{
    public List<ReferenceERP> details;
}


package com.pilog.plontology.service;

import com.pilog.plontology.payloads.GenerateClassTermDTO;

public interface IClassIdService {
    String generateTerm(GenerateClassTermDTO generateClassTermDTO);
}

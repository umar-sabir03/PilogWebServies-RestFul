package com.pilog.plontology.service;

import com.pilog.plontology.payloads.PropRequestDTO;
import com.pilog.plontology.payloads.PropertyResponseDTO;

public interface IClassPropertySearch {
     PropertyResponseDTO fetchPropValues(PropRequestDTO request) ;

}

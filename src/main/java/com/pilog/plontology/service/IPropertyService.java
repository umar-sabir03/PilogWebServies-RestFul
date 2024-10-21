package com.pilog.plontology.service;

import com.pilog.plontology.payloads.PropertyRequest;
import com.pilog.plontology.payloads.PropertyResponse;

public interface IPropertyService {
    PropertyResponse listProperties(PropertyRequest request);
}

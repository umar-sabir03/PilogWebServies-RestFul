package com.pilog.plontology.service;

import com.pilog.plontology.payloads.CharacteristicERP;
import com.pilog.plontology.payloads.CharacteristicRequest;

import java.util.List;

public interface ICharacteristicService {
    List<CharacteristicERP> duplicateRecords(CharacteristicRequest request);
}

package com.pilog.plontology.service;

import com.pilog.plontology.payloads.CharacteristicsRequest;
import com.pilog.plontology.payloads.CharacteristicsResponse;

public interface ISearchCharacteristicsService {
    CharacteristicsResponse list(CharacteristicsRequest request);
}

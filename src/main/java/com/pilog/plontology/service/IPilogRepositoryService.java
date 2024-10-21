package com.pilog.plontology.service;

import com.pilog.plontology.payloads.RepositorySearchRequest;

public interface IPilogRepositoryService {
  String searchRepository(RepositorySearchRequest request);
   String generateInStr(String value);
}

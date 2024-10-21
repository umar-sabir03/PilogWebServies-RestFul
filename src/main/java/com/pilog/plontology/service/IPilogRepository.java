package com.pilog.plontology.service;

import com.pilog.plontology.payloads.RepositorySearchRequest;

import java.sql.SQLException;

public interface IPilogRepository {
String searchRepository(RepositorySearchRequest request) throws SQLException;
}

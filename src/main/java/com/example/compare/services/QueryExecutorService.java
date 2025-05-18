package com.example.compare.services;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

public interface QueryExecutorService {
    JsonNode executeQuery(String sql);
    JsonNode executeQuery(String sql, Map<String, Object> parameters);
    List<String> getQueryMetadata(String sql);
}
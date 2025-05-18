package com.example.compare.services;


import com.example.compare.util.ResultSetToJsonMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class QueryExecutorServiceImpl implements QueryExecutorService {

    @Qualifier("CustomerNamedTemplate")
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Qualifier("CustomerTemplate")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*public QueryExecutorServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }*/

    @Override
    public JsonNode executeQuery(String sql) {
        return executeQuery(sql, Collections.emptyMap());
    }

    @Override
    public JsonNode executeQuery(String sql, Map<String, Object> parameters) {
        return namedParameterJdbcTemplate.query(sql, parameters, ResultSetToJsonMapper::mapResultSetToJson);
    }

    @Override
    public List<String> getQueryMetadata(String sql) {
        return jdbcTemplate.query(sql, ResultSetToJsonMapper::extractColumnMetadata);
    }
}
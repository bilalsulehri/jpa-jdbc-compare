package com.example.compare.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetToJsonMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode mapResultSetToJson(ResultSet rs) throws SQLException {
        ArrayNode arrayNode = objectMapper.createArrayNode();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (rs.next()) {
            ObjectNode rowNode = objectMapper.createObjectNode();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = JdbcUtils.lookupColumnName(metaData, i);
                Object value = JdbcUtils.getResultSetValue(rs, i);
                addValueToNode(rowNode, columnName, value);
            }
            arrayNode.add(rowNode);
        }
        return arrayNode;
    }

    public static List<String> extractColumnMetadata(ResultSet rs) throws SQLException {
        List<String> columns = new ArrayList<>();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            columns.add(metaData.getColumnName(i) + " (" + metaData.getColumnTypeName(i) + ")");
        }
        return columns;
    }

    private static void addValueToNode(ObjectNode node, String key, Object value) {
      //  System.out.println(key +", "+ value+" "+value.getClass().getName());
        if (value == null) {
            node.putNull(key);
        } else if (value instanceof Double ) {
            node.put(key, (Double) value);
        } else if (value instanceof Integer) {
            node.put(key, (Integer) value);
        } else if (value instanceof Long) {
            node.put(key, (Long) value);
        }
        else if (value instanceof Boolean) {
            node.put(key, (Boolean) value);
        } else {
            node.put(key, value.toString());
        }
    }
}
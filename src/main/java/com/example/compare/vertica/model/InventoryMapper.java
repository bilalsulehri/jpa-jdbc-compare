package com.example.compare.vertica.model;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryMapper implements RowMapper<InventoryDao> {
    @Override
    public InventoryDao mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new InventoryDao(rs.getInt(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getInt(4),
                rs.getInt(5),
                rs.getDate(6));
    }
}

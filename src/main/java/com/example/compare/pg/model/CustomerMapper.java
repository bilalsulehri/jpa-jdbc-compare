package com.example.compare.pg.model;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<CustomerDao> {
    @Override
    public CustomerDao mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CustomerDao(rs.getString(1), rs.getString(2),rs.getString(3));
    }
}

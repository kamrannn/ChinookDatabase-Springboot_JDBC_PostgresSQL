package com.app.chinook.mapper;

import com.app.chinook.model.CustomerCountry;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Customer country row mapper which will be used to map the database record to a specific java object.
 */
public class CustomerCountryRowMapper implements RowMapper<CustomerCountry> {

    @Override
    public CustomerCountry mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerCountry customerCountry = new CustomerCountry();
        customerCountry.setCountry(rs.getString("country"));
        return customerCountry;

    }
}

package com.app.chinook.mapper;

import com.app.chinook.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Customer row mapper which will be used to map the database record to a specific java object.
 */
public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

        Customer customer = new Customer();
        customer.setCustomer_id(rs.getInt("customer_id"));
        customer.setFirst_name(rs.getString("first_name"));
        customer.setLast_name(rs.getString("last_name"));
        customer.setCountry(rs.getString("country"));
        customer.setPostal_code(rs.getString("postal_code"));
        customer.setPhone(rs.getString("phone"));
        customer.setEmail(rs.getString("email"));
        return customer;

    }
}

package com.app.chinook.mapper;

import com.app.chinook.model.CustomerSpender;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * The type Customer spender row mapper which will be used to map the database record to a specific java object.
 */
public class CustomerSpenderRowMapper implements RowMapper<CustomerSpender> {

    @Override
    public CustomerSpender mapRow(ResultSet rs, int rowNum) throws SQLException {

        CustomerSpender customerSpender = new CustomerSpender();
        customerSpender.setCustomer_id(rs.getInt("customer_id"));
        customerSpender.setFirst_name(rs.getString("first_name"));
        customerSpender.setLast_name(rs.getString("last_name"));


        customerSpender.setCompany(rs.getString("company"));
        customerSpender.setAddress(rs.getString("address"));
        customerSpender.setCity(rs.getString("city"));
        customerSpender.setState(rs.getString("state"));

        customerSpender.setCountry(rs.getString("country"));
        customerSpender.setPostal_code(rs.getString("postal_code"));
        customerSpender.setPhone(rs.getString("phone"));
        customerSpender.setFax(rs.getString("fax"));
        customerSpender.setEmail(rs.getString("email"));
        customerSpender.setSupport_rep_id(rs.getInt("support_rep_id"));
        return customerSpender;
    }
}

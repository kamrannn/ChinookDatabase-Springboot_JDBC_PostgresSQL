package com.app.chinook.mapper;

import com.app.chinook.model.CustomerGenre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Customer genre row mapper.
 */
public class CustomerGenreRowMapper implements RowMapper<CustomerGenre> {

    @Override
    public CustomerGenre mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerGenre customerGenre = new CustomerGenre();
        customerGenre.setName(rs.getString("name"));
        customerGenre.setTracks_count(rs.getString("tracks_count"));
        return customerGenre;

    }
}


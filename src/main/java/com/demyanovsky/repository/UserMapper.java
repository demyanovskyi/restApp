package com.demyanovsky.repository;


import com.demyanovsky.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        String tm = resultSet.getString("id");
        UUID id = UUID.fromString(tm);
        user.setId(id);
        user.setName(resultSet.getString("name"));
        return user;
    }
}
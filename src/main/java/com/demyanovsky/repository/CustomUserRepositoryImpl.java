package com.demyanovsky.repository;

import com.demyanovsky.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CustomUserRepositoryImpl implements CustomUserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void modify(User user) {
        String sql = "UPDATE users SET  name = ? " + " WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getId());

    }

}

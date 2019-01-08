package com.demyanovsky.repository.impl;


import com.demyanovsky.repository.UserDao;
import com.demyanovsky.domain.User;

import com.demyanovsky.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao<User> {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void save(User user) {
        String sql = "INSERT INTO users " +
                "(ID, NAME) VALUES ( ?, ?)";
        jdbcTemplate.update(sql, new Object[]{
                user.getId(), user.getName()
        });

    }


    @Override
    public List<User> getAll() {
        final String sql = "SELECT * FROM users";
        List<User> usersList = jdbcTemplate.query(sql, new UserMapper() {
        });
        return usersList;

    }


    @Override
    public void deliteUsebyID(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }


    @Override
    public User getUserById(Long id) {
        String sql = "SELECT * FROM users WHERE ID = ?";
        return (User) jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper() {
        });
    }
}

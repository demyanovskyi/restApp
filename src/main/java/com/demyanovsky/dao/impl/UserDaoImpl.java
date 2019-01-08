package com.demyanovsky.dao.impl;


import com.demyanovsky.dao.UserDao;
import com.demyanovsky.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

/*
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("your url");
        dataSource.setUsername( "username" );
        dataSource.setPassword( "password" );
        return dataSource;
    }*/

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
        List<User> usersList = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));

                return user;
            }
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
        return (User) jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int rwNumber) throws SQLException {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));

                return user;


            }
        });
    }
}


package com.demyanovsky.repository;


import com.demyanovsky.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void save(User user) {
        String sql = "INSERT INTO users " +
                "(ID, NAME) VALUES ( ?, ?)";
        jdbcTemplate.update(sql, new Object[]{
                user.getId(), user.getName()});
    }

    public List<User> getAll() {
        final String sql = "SELECT * FROM users";
        List<User> usersList = jdbcTemplate.query(sql, new UserMapper());
        return usersList;
    }

    public void deliteUsebyID(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public User getUserById(Long id) {
        String sql = "SELECT * FROM users WHERE ID = ?";
        return (User) jdbcTemplate.query(sql, new Object[]{id}, new UserMapper());
    }
}

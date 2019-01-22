package com.demyanovsky.repository;

import com.demyanovsky.domain.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class OrderMapper implements RowMapper<Order> {
    public Order mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Order order = new Order();
        String tmId = resultSet.getString("id");
        UUID id = UUID.fromString(tmId);
        order.setId(id);

        String tmUserId = resultSet.getString("user_id");
        UUID idUser = UUID.fromString(tmUserId);
        order.setUserId(idUser);
        return order;
    }

}

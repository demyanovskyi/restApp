package com.demyanovsky.repository;

import com.demyanovsky.domain.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProductMapper implements RowMapper<Product> {
    public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Product product = new Product();
        String tm = resultSet.getString("id");
        UUID id = UUID.fromString(tm);
        product.setId(id);
        product.setName(resultSet.getString("product_name"));
        product.setPrice(resultSet.getDouble("price"));
        return product;
    }

}

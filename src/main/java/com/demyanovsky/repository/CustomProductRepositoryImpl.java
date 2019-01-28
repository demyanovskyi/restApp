package com.demyanovsky.repository;

import com.demyanovsky.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CustomProductRepositoryImpl implements  CustomProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void saveProduct(Product product) {
        String sql = "INSERT INTO public.product (id, product_name, price) values(?,?,?)";
        jdbcTemplate.update(sql, new Object[]{product.getId(), product.getName(), product.getPrice()});

    }

    @Override
    public void modify(Product product) {
        String sql = "UPDATE product SET  product_name = ?, price = ?  WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getId());

    }
}

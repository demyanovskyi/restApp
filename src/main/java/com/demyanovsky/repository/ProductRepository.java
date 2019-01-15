package com.demyanovsky.repository;

import com.demyanovsky.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void save(Product product) {
        String sql = "INSERT INTO public.product (id, product_name, price) values(?,?,?)";
        jdbcTemplate.update(sql, new Object[]{product.getId(), product.getName(), product.getPrice()});
    }

    public List<Product> getAll() {
        final String sql = "SELECT * FROM product";
        List<Product> productList = jdbcTemplate.query(sql, new ProductMapper());
        return productList;
    }

    public void deletebyID(UUID id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Product getProductById(UUID id) {
        String sql = "SELECT * FROM product WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductMapper());
    }

    public void modifyName(Product product) {
        String sql = "UPDATE product SET  product_name = ?, price = ?  WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getId());
    }
}

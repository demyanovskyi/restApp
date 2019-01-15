package com.demyanovsky.services.impl;

import com.demyanovsky.domain.Product;
import com.demyanovsky.exceptions.IncorrectProductException;
import com.demyanovsky.exceptions.ProductNotFoundException;
import com.demyanovsky.repository.ProductRepository;
import com.demyanovsky.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    private static List<Product> products = new ArrayList<>();

    public static List<Product> getProducts() {
        return products;
    }

    @Override
    public void save(Product product) {
        try {
            productRepository.save(product);
        } catch (Exception e) {
            throw new IncorrectProductException(product.getId());
        }
    }

    @Override
    public void deleteById(UUID id) {
        try {
            productRepository.deletebyID(id);
        } catch (Exception e) {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById(UUID id) {
        try {
            return productRepository.getProductById(id);
        } catch (Exception e) {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public Product modify(Product product) {
        try {
            productRepository.modify(product);
        }catch (Exception e){
            throw new IncorrectProductException(product.getId());
        }
        return productRepository.getProductById(product.getId());

    }
}

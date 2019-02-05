package com.demyanovsky.services.impl;

import com.demyanovsky.domain.Product;
import com.demyanovsky.exceptions.ProductNotFoundException;
import com.demyanovsky.repository.ProductRepository;
import com.demyanovsky.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        Objects.requireNonNull(product);
        return productRepository.save(product);
    }

    @Override
    public void deleteById(UUID id) {
        try {
            productRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().iterator().forEachRemaining(products::add);
        return products;
    }

    @Override
    public Product getById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public void modify(Product product) {
        Objects.requireNonNull(product);
        productRepository.save(product);
    }
}

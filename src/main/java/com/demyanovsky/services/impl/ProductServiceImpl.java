package com.demyanovsky.services.impl;

import com.demyanovsky.domain.Product;
import com.demyanovsky.exceptions.ProductNotFoundException;
import com.demyanovsky.exceptions.ProductNotValidException;
import com.demyanovsky.repository.ProductRepository;
import com.demyanovsky.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    private static List<Product> products = new ArrayList<>();

    public static List<Product> getProducts() {
        return products;
    }

    @Override
    public Product save(Product product) {
        try {
            return productRepository.save(product);
        } catch (NoSuchElementException e) {
            throw new ProductNotValidException(product.getId());
        }
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
    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public void modify(Product product) {
        try {
            productRepository.save(product);
        } catch (NoSuchElementException e) {
            throw new ProductNotFoundException(product.getId());
        }
    }
}

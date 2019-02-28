package com.demyanovsky.services.impl;

import com.demyanovsky.domain.Product;
import com.demyanovsky.exceptions.ProductNotFoundException;
import com.demyanovsky.repository.ProductRepository;
import com.demyanovsky.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    private Pageable createPageRequest(int page, int size) {
        return PageRequest.of(page, size, Sort.by("productName"));
    }

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
    public List<Product> getAll(int page, int limit) {
        if (limit < 1) {
            throw new IllegalArgumentException("Size must not be less than one!");
        }
        List<Product> list = new ArrayList<>();
        for (Product product : productRepository.findAll(createPageRequest(page, limit))) {
            list.add(product);
        }
        return list;
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

package com.demyanovsky.services.impl;

import com.demyanovsky.domain.Product;
import com.demyanovsky.exceptions.ProductNotFoundException;
import com.demyanovsky.repository.ProductRepository;
import com.demyanovsky.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<Product> getAll(Integer page, Integer limit) {
        if (limit == null) {
            limit = 50;
            if (page == null) {
                page = 0;
            }
        }
        return productRepository.findAll(createPageRequest(page, limit));
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

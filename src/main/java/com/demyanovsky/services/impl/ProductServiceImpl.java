package com.demyanovsky.services.impl;

import com.demyanovsky.domain.Product;
import com.demyanovsky.exceptions.ProductNotFoundException;
import com.demyanovsky.repository.ProductRepository;
import com.demyanovsky.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

      return   productRepository.save(product);
    }

    @Override
    public void deleteById(UUID id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getById(UUID id) {

            return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        }


    @Override
    public Optional<Product> modify(Product product) {
        try {
            productRepository.save(product);
        } catch (Exception e) {
            throw new ProductNotFoundException(product.getId());
        }
        return productRepository.findById(product.getId());


    }
}

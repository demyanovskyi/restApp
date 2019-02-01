package com.demyanovsky.services;

import com.demyanovsky.domain.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    Product save(Product product);


    void deleteById(UUID id);

   Iterable<Product> getAll();


    Product getById(UUID id);

    Optional<Product> modify(Product product);
}

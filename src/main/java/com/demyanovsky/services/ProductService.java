package com.demyanovsky.services;

import com.demyanovsky.domain.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    /**
     * Add the product .
     *
     * @param product
     */
    Product save(Product product);

    /**
     * Delete the product by id.
     *
     * @param id
     */
    void deleteById(UUID id);

    /**
     * Get the list of products.
     *
     * @return list of products
     */
   Iterable<Product> getAll();

    /**
     * Get the product by id.
     *
     * @param id
     * @return product
     */
    Product getById(UUID id);

    /**
     * Update the Product.
     *
     * @param product
     * @return product
     */
    Optional<Product> modify(Product product);
}

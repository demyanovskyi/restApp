package com.demyanovsky.services;

import com.demyanovsky.domain.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    /**
     * Add the product .
     *
     * @param product
     */
    Product save(Product product);

    /**
     * Delete the product by it's id.
     *
     * @param id the id
     */
    void deleteById(UUID id);

    /**
     * Get the list of products.
     *
     * @param page
     * @param limit
     * @return list of products
     */
    List<Product> getAll(int page, int limit);

    /**
     * Get the Product by id.
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
    void modify(Product product);
}

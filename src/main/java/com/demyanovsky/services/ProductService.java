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
    void save(Product product);

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
    List<Product> getAll();

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
    Product modify(Product product);
}

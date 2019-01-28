package com.demyanovsky.repository;

import com.demyanovsky.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<Product, UUID>, CustomProductRepository {
}

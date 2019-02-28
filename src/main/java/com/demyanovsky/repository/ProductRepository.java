package com.demyanovsky.repository;

import com.demyanovsky.domain.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, UUID> {
    Page<Product> findAll(Pageable pageable);

}
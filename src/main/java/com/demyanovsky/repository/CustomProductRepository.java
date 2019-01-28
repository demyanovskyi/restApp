package com.demyanovsky.repository;

import com.demyanovsky.domain.Product;

public interface CustomProductRepository  {

    public void saveProduct(Product user);
    public  void  modify(Product user);
}

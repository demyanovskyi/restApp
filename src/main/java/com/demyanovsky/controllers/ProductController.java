package com.demyanovsky.controllers;

import com.demyanovsky.domain.Product;
import com.demyanovsky.exceptions.ProductNotFoundException;
import com.demyanovsky.services.ProductCRUDConstants;
import com.demyanovsky.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = ProductCRUDConstants.CREATE_PRODUCT, method = RequestMethod.POST)
    private ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @RequestMapping(value = ProductCRUDConstants.GET_ALL_PRODUCTS, method = RequestMethod.GET)
    private ResponseEntity<List<Product>> getProductList() {
        List<Product> products = productService.getAll();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @RequestMapping(value = ProductCRUDConstants.GET_PRODUCT, method = RequestMethod.GET)
    private ResponseEntity<Product> getProduct(@PathVariable("id") UUID id) {
        Product product = productService.getById(id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @RequestMapping(value = ProductCRUDConstants.DELETE_PRODUCT, method = RequestMethod.DELETE)
    private ResponseEntity<Product> deleteProduct(@PathVariable("id") UUID id) {
        productService.deleteById(id);
        return new ResponseEntity<Product>(HttpStatus.OK);
    }

    @RequestMapping(value = ProductCRUDConstants.UPDATE_PRODUCT, method = RequestMethod.PUT)
    private ResponseEntity<Product> updateName(@PathVariable ("id") UUID id, @RequestBody Product product) {
        if(product.getId().equals(id)) {
            productService.modify(product);
        }else{
            throw new ProductNotFoundException(id);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
}

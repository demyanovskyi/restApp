package com.demyanovsky.controllers;

import com.demyanovsky.domain.Product;
import com.demyanovsky.exceptions.ProductNotValidException;
import com.demyanovsky.services.ProductService;
import com.demyanovsky.services.mappingConstants.ProductCRUDConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @RequestMapping(value = ProductCRUDConstants.CREATE_PRODUCT, method = RequestMethod.POST)
    private ResponseEntity<Product> createProduct(@RequestBody Product product) {
        logger.info("Call method createProduct from ProductController");
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @RequestMapping(value = ProductCRUDConstants.GET_ALL_PRODUCTS, method = RequestMethod.GET)
    private ResponseEntity<List<Product>> getProductList(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        logger.info("Call method getProductList from ProductController");
        List<Product> products = productService.getAll(page, limit);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @RequestMapping(value = ProductCRUDConstants.GET_PRODUCT, method = RequestMethod.GET)
    private ResponseEntity<Product> getProduct(@PathVariable("id") UUID id) {
        logger.info("Call method getProduct from ProductController");
        Product product = productService.getById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = ProductCRUDConstants.DELETE_PRODUCT, method = RequestMethod.DELETE)
    private ResponseEntity<Product> deleteProduct(@PathVariable("id") UUID id) {
        logger.info("Call method deleteProduct from ProductController");
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = ProductCRUDConstants.UPDATE_PRODUCT, method = RequestMethod.PUT)
    private ResponseEntity<Product> updateProduct(@PathVariable("id") UUID id, @RequestBody Product product) {
        logger.info("Call method updateProduct from ProductController");
        if (product.getId().equals(id)) {
            productService.modify(product);
        } else {
            throw new ProductNotValidException(id);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}

package com.demyanovsky.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderDTO {

    public OrderDTO() {
    }

    @JsonProperty
    List<UUID> productList;


    public OrderDTO(List<UUID> productList) {
        this.productList = productList;
    }

    public List<UUID> productList() {
        return productList;
    }

    public List<UUID> getProductList() {
        return productList;
    }

    public void setProductList(List<UUID> productList) {
        this.productList = productList;
    }

    public void setProducts(List<UUID> products) {
        this.productList = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(productList, orderDTO.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productList);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "products=" + productList +
                '}';
    }
}

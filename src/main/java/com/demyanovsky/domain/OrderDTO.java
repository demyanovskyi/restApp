package com.demyanovsky.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderDTO {
    @JsonProperty
    List<UUID> products;

    public OrderDTO() {
    }

    public OrderDTO(List<UUID> productList) {
        this.products = productList;
    }

    public List<UUID> productList() {
        return products;
    }

    public List<UUID> getProductList() {
        return products;
    }

    public void setProductList(List<UUID> productList) {
        this.products = productList;
    }

    public void setProducts(List<UUID> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(products, orderDTO.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "products=" + products +
                '}';
    }
}

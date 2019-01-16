package com.demyanovsky.domain;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private UUID id;
    private UUID userId;
    private UUID productId;
    private static List<Product> products;


    public Order(UUID id, UUID userId, UUID productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;

    }

    public Order() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static void setProducts(List<Product> products) {
        Order.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId().equals(order.getId()) &&
                getUserId().equals(order.getUserId()) &&
                getProductId().equals(order.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getProductId());
    }
}

package com.demyanovsky.domain;

import java.util.Objects;
import java.util.UUID;

public class Order {
    private UUID id;
    private UUID userId;
    private UUID productId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUser_id() {
        return userId;
    }

    public void setUser_id(UUID user_id) {
        this.userId = user_id;
    }

    public UUID getProduct_id() {
        return productId;
    }

    public void setProduct_id(UUID product_id) {
        this.productId = product_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId().equals(order.getId()) &&
                getUser_id().equals(order.getUser_id()) &&
                getProduct_id().equals(order.getProduct_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser_id(), getProduct_id());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user_id=" + userId +
                ", product_id=" + productId +
                '}';
    }
}

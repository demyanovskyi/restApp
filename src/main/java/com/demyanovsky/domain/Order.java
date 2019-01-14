package com.demyanovsky.domain;

import java.util.Objects;
import java.util.UUID;

public class Order {
    private UUID id;
    private UUID user_id;
    private UUID product_id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getProduct_id() {
        return product_id;
    }

    public void setProduct_id(UUID product_id) {
        this.product_id = product_id;
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
                ", user_id=" + user_id +
                ", product_id=" + product_id +
                '}';
    }
}

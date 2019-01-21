package com.demyanovsky.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {
    @JsonProperty
    private UUID id;

    @JsonProperty
    private UUID userId;
    @JsonIgnore
    private UUID productID;
    @JsonProperty
    private List<UUID> listProductID;

    public Order(UUID id, UUID userId) {
        this.id = id;
        this.userId = userId;
    }

    public Order() {
    }

    public Order(UUID id, UUID userId, List<UUID> listProductID) {
        this.id = id;
        this.userId = userId;
        this.listProductID = listProductID;
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

    public UUID getProductID() {
        return productID;
    }

    public void setProductID(UUID productID) {
        this.productID = productID;
    }


    public List<UUID> getListProductID() {
        return listProductID;
    }

    public void setListProductID(List<UUID> listProductID) {
        this.listProductID = listProductID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) &&
                Objects.equals(getUserId(), order.getUserId()) &&
                Objects.equals(getProductID(), order.getProductID()) &&
                Objects.equals(getListProductID(), order.getListProductID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getProductID(), getListProductID());
    }

    @Override
    public String
    toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", productID=" + productID +
                ", listProductID=" + listProductID +
                '}';
    }
}

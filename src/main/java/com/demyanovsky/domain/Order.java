package com.demyanovsky.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "shop_order")
public class Order {

    @JsonProperty
    @Id
    private UUID id;

    @JsonProperty
    private UUID userId;

    @JsonProperty
    private List<ProductRef> products = new ArrayList<>();

    public Order(UUID userId) {
        this.userId = userId;
    }

    public void addProduct(Product product) {
        products.add(createProductRef(product));
    }

    private ProductRef createProductRef(Product product) {
        return new ProductRef(product.getId());
    }

    public Order() {
    }


    public Order(List<ProductRef> listProductID) {
        this.products = listProductID;

    }

    public Order(UUID userId, List<ProductRef> listProductID) {
        this.userId = userId;
        this.products = listProductID;
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

    public List<ProductRef> getListProductID() {
        return products;
    }

    public void setListProductID(List<ProductRef> listProductID) {
        this.products = listProductID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) &&
                Objects.equals(getUserId(), order.getUserId()) &&
                Objects.equals(getListProductID(), order.getListProductID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getListProductID());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", listProductID=" + products +
                '}';
    }

}


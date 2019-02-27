package com.demyanovsky.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Entity(name = "Order")
@Table(name = "shop_order")
public class Order {
    @JsonProperty
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @Id
    private UUID id;

    @JsonProperty
    private UUID userId;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "shop_order_product",
            joinColumns = @JoinColumn(name = "shop_order"),
            inverseJoinColumns = @JoinColumn(name = "product")
    )
    @JsonProperty

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProducts(Iterable<Product> products) {
        for (Product product : products) {
            this.products.add(product);
        }
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Order() {
    }

    public Order(UUID userId) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, products);
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


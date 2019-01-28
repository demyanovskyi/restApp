package com.demyanovsky.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.util.UUID;

public class Product {
    @JsonProperty
    @Id
    private UUID id;
    private String productName;
    private Double price;

    public UUID getId() {
        return id;
    }

    public Product(String productName, Double price) {
        this.productName = productName;
        this.price = price;
    }


    public Product() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return productName;
    }

    public void setName(String name) {
        this.productName = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                getId().equals(product.getId()) &&
                getName().equals(product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}

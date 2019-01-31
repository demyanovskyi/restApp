package com.demyanovsky.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "shop_order_product")
public class ProductRef {
    @JsonProperty
    @Id
    private UUID product;


    public ProductRef() {
    }


    public ProductRef(UUID productId) {
        this.product = productId;

    }


    public void setProductId(UUID productId) {
        this.product = productId;
    }

    public UUID getProductId() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRef that = (ProductRef) o;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    @Override
    public String toString() {
        return "ProductRef{" +
                "product=" + product +
                '}';
    }
}



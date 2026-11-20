package com.santander.base.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table (name="product")
public class Product {

    @Id
    @Column(length = 6)
    private String ProductId;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ProductType type;

    protected Product(){

    }

    public Product(String productId, String name, ProductType type){

        this.ProductId = productId;
        this.name = name;
        this.type = type;
    }

    public String getProductId() {
        return ProductId;
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }
}

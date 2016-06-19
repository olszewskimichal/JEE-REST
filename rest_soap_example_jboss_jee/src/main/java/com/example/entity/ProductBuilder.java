package com.example.entity;

import java.math.BigDecimal;

public class ProductBuilder {
	private String name;
    private String imageUrl = "http://localhost/image";
    private String description = "description";
    private BigDecimal price=new BigDecimal("10.00");


    public ProductBuilder(String name) {
        this.name = name;
    }

    public ProductBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Product build() {
        return new Product(name, imageUrl, description, price);
    }
}

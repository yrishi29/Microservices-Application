package com.example.orderservice.dto;

import java.math.BigDecimal;

public record OrderRequest(Long id, String skuCode, BigDecimal price, Integer quantity) {
    public OrderRequest(Long id, String skuCode, BigDecimal price, Integer quantity) {
        this.id = id;
        this.skuCode = skuCode;
        this.price = price;
        this.quantity = quantity;
    }

    public Long id() {
        return this.id;
    }

    public String skuCode() {
        return this.skuCode;
    }

    public BigDecimal price() {
        return this.price;
    }

    public Integer quantity() {
        return this.quantity;
    }
}


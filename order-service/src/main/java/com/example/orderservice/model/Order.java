package com.example.orderservice.model;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(
        name = "t_orders"
)
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String orderNumber;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

    public Long getId() {
        return this.id;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public String getSkuCode() {
        return this.skuCode;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setOrderNumber(final String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setSkuCode(final String skuCode) {
        this.skuCode = skuCode;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public Order() {
    }

    public Order(final Long id, final String orderNumber, final String skuCode, final BigDecimal price, final Integer quantity) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.skuCode = skuCode;
        this.price = price;
        this.quantity = quantity;
    }
}



package com.example.inventoryservice.model;

import jakarta.persistence.*;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "t_inventory"
)
public class Inventory {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Column(
            name = "skuCode"
    )
    private String skuCode;

    private Integer quantity;

}


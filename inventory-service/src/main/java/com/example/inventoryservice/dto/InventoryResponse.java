package com.example.inventoryservice.dto;

public record InventoryResponse(String skuCode, boolean isInStock) {

    public InventoryResponse(String skuCode, boolean isInStock) {
        this.skuCode = skuCode;
        this.isInStock = isInStock;
    }

    public String skuCode() {
        return this.skuCode;
    }

    public boolean isInStock() {
        return this.isInStock;
    }
}

package com.example.inventoryservice.service;

import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.example.inventoryservice.model.Inventory;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(
            readOnly = true
    )
    public boolean isInStock(String skuCode, Integer quantity) {
        return this.inventoryRepository.isStockAvailable(skuCode, quantity);
    }

    @Transactional(
            readOnly = true
    )
    public boolean isInStocks(String skuCode, Integer quantity) {
        return inventoryRepository.findBySkuCode(skuCode)
                .map(inventory -> inventory.getQuantity() >= quantity)
                .orElse(false);
    }

    public InventoryService(final InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
}


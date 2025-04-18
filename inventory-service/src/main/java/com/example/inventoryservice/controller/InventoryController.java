package com.example.inventoryservice.controller;

import com.example.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/inventory"})
public class InventoryController {

    private final InventoryService inventoryService;
    public InventoryController(final InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return this.inventoryService.isInStock(skuCode, quantity);
    }


}

package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, int quantity);

    Optional<Inventory> findBySkuCode(String skuCode);

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Inventory i WHERE i.skuCode = :skuCode AND i.quantity >= :quantity")
    boolean isStockAvailable(@Param("skuCode") String skuCode, @Param("quantity") int quantity);
}


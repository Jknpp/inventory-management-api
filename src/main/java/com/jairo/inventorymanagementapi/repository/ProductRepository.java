package com.jairo.inventorymanagementapi.repository;

import com.jairo.inventorymanagementapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsBySkuIgnoreCase(String sku);

    List<Product> findByNameContainingIgnoreCase(String name);

    @Query("""
    SELECT p
    FROM Product p
    WHERE p.quantity <= p.minimumStock
""")
    List<Product> findLowStockProducts();
}

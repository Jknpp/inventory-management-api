package com.jairo.inventorymanagementapi.repository;

import com.jairo.inventorymanagementapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsBySkuIgnoreCase(String sku);
}

package com.jairo.inventorymanagementapi.repository;

import com.jairo.inventorymanagementapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByNameIgnoreCase(String name);
}
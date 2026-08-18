package com.jairo.inventorymanagementapi.service;

import com.jairo.inventorymanagementapi.dto.ProductRequest;
import com.jairo.inventorymanagementapi.dto.ProductResponse;
import com.jairo.inventorymanagementapi.entity.Category;
import com.jairo.inventorymanagementapi.entity.Product;
import com.jairo.inventorymanagementapi.exception.DuplicateResourceException;
import com.jairo.inventorymanagementapi.exception.ResourceNotFoundException;
import com.jairo.inventorymanagementapi.repository.CategoryRepository;
import com.jairo.inventorymanagementapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductResponse create(ProductRequest request) {

        if (productRepository.existsBySkuIgnoreCase(request.sku())) {
            throw new DuplicateResourceException("Product SKU already exists");
        }

        Category category = findCategoryById(request.categoryId());

        Product product = new Product(
                request.name(),
                request.description(),
                request.sku(),
                request.price(),
                request.quantity(),
                request.minimumStock(),
                category
        );

        return toResponse(productRepository.save(product));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductResponse findById(Long id) {
        return toResponse(findProductById(id));
    }

    public ProductResponse update(Long id, ProductRequest request) {

        Product product = findProductById(id);

        Category category = findCategoryById(request.categoryId());

        boolean skuWasChanged = !product.getSku().equalsIgnoreCase(request.sku());

        if (skuWasChanged && productRepository.existsBySkuIgnoreCase(request.sku())) {
            throw new DuplicateResourceException("Product SKU already exists");
        }

        product.update(
                request.name(),
                request.description(),
                request.sku(),
                request.price(),
                request.quantity(),
                request.minimumStock(),
                category
        );

        return toResponse(productRepository.save(product));
    }

    public void delete(Long id) {
        productRepository.delete(findProductById(id));
    }

    public List<ProductResponse> findByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ProductResponse> findLowStockProducts() {
        return productRepository.findLowStockProducts()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getSku(),
                product.getPrice(),
                product.getQuantity(),
                product.getMinimumStock(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
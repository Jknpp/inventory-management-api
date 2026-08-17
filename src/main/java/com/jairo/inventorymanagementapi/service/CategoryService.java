package com.jairo.inventorymanagementapi.service;

import com.jairo.inventorymanagementapi.dto.CategoryRequest;
import com.jairo.inventorymanagementapi.dto.CategoryResponse;
import com.jairo.inventorymanagementapi.entity.Category;
import com.jairo.inventorymanagementapi.exception.DuplicateResourceException;
import com.jairo.inventorymanagementapi.exception.ResourceNotFoundException;
import com.jairo.inventorymanagementapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponse create(CategoryRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.name())) {
            throw new DuplicateResourceException("Category name already exists");
        }

        Category category = new Category(request.name(), request.description());

        return toResponse(categoryRepository.save(category));
    }

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CategoryResponse findById(Long id) {
        return toResponse(findEntityById(id));
    }

    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = findEntityById(id);

        boolean nameWasChanged = !category.getName().equalsIgnoreCase(request.name());

        if (nameWasChanged && categoryRepository.existsByNameIgnoreCase(request.name())) {
            throw new DuplicateResourceException("Category name already exists");
        }

        category.update(request.name(), request.description());

        return toResponse(categoryRepository.save(category));
    }

    public void delete(Long id) {
        categoryRepository.delete(findEntityById(id));
    }

    private Category findEntityById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }
}
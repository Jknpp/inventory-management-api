package com.jairo.inventorymanagementapi.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must have at most 100 characters")
        String name,

        @Size(max = 255, message = "Description must have at most 255 characters")
        String description,

        @NotBlank(message = "SKU is required")
        @Size(max = 100, message = "SKU must have at most 100 characters")
        String sku,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
        @Digits(integer = 8, fraction = 2, message = "Price has invalid format")
        BigDecimal price,

        @NotNull(message = "Quantity is required")
        @Min(value = 0, message = "Quantity cannot be negative")
        Integer quantity,

        @Min(value = 0, message = "Minimum stock cannot be negative")
        Integer minimumStock,

        @NotNull(message = "Category ID is required")
        Long categoryId
) {
}

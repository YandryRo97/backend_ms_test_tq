package com.tarpuq.inventory_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ReserveRequest(
    @NotBlank String itemCode,
    @Min(1) int quantity
) {}
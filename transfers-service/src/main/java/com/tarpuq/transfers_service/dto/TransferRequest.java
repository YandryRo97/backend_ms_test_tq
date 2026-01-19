package com.tarpuq.transfers_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TransferRequest(
    @NotBlank String itemCode,
    @Min(1) int quantity,
    @NotBlank String fromWarehouse,
    @NotBlank String toWarehouse
) {}
package com.tarpuq.inventory_service.dto;

public record ReserveResponse(
    boolean ok,
    String message,
    int remaining
) {}

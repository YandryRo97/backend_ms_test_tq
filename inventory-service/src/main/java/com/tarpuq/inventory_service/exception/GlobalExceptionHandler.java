package com.tarpuq.inventory_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InsufficientStockException.class)
  public ResponseEntity<?> handleStock(InsufficientStockException e) {
    return ResponseEntity.badRequest().body(Map.of("ok", false, "error", e.getMessage()));
  }
}

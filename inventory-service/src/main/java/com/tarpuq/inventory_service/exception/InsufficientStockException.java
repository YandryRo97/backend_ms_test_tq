package com.tarpuq.inventory_service.exception;

public class InsufficientStockException extends RuntimeException {
  public InsufficientStockException(String msg) { super(msg); }
}
package com.tarpuq.inventory_service.repo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryInventoryRepository {

  private final Map<String, Integer> stock = new ConcurrentHashMap<>(
      Map.of("RES-001", 50, "CAP-777", 20)
  );

  public int getAvailable(String itemCode) {
    return stock.getOrDefault(itemCode, 0);
  }

  public void setAvailable(String itemCode, int qty) {
    stock.put(itemCode, qty);
  }
}
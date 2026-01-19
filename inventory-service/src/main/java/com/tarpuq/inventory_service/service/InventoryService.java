package com.tarpuq.inventory_service.service;

import com.tarpuq.inventory_service.dto.ReserveRequest;
import com.tarpuq.inventory_service.dto.ReserveResponse;
import com.tarpuq.inventory_service.exception.InsufficientStockException;
import com.tarpuq.inventory_service.repo.InMemoryInventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

  private final InMemoryInventoryRepository repo;

  public InventoryService(InMemoryInventoryRepository repo) {
    this.repo = repo;
  }

  public ReserveResponse reserve(ReserveRequest req) {
    int available = repo.getAvailable(req.itemCode());

    if (available < req.quantity()) {
      throw new InsufficientStockException("No hay stock suficiente. Disponible: " + available);
    }

    int remaining = available - req.quantity();
    repo.setAvailable(req.itemCode(), remaining);

    return new ReserveResponse(true, "Reserva aplicada", remaining);
  }

  public int getStock(String itemCode) {
    return repo.getAvailable(itemCode);
  }
}
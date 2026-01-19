package com.tarpuq.inventory_service.api;

import com.tarpuq.inventory_service.dto.ReserveRequest;
import com.tarpuq.inventory_service.dto.ReserveResponse;
import com.tarpuq.inventory_service.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

  private final InventoryService service;

  public InventoryController(InventoryService service) {
    this.service = service;
  }

  @GetMapping("/{itemCode}")
  public ResponseEntity<?> get(@PathVariable String itemCode) {
    int qty = service.getStock(itemCode);
    return ResponseEntity.ok(Map.of("itemCode", itemCode, "quantity", qty));
  }

  @PostMapping("/reserve")
  public ResponseEntity<ReserveResponse> reserve(@Valid @RequestBody ReserveRequest req) {
    return ResponseEntity.ok(service.reserve(req));
  }
}
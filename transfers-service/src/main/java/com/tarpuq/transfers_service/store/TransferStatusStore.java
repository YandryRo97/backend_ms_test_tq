package com.tarpuq.transfers_service.store;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TransferStatusStore {

  public record Status(String transferId, String status, String message, Instant updatedAt) {}

  private final ConcurrentHashMap<String, Status> db = new ConcurrentHashMap<>();

  public void queued(String id) {
    db.put(id, new Status(id, "QUEUED", "En cola para procesar", Instant.now()));
  }

  public void success(String id, String message) {
    db.put(id, new Status(id, "SUCCESS", message, Instant.now()));
  }

  public void failed(String id, String message) {
    db.put(id, new Status(id, "FAILED", message, Instant.now()));
  }

  public Status get(String id) {
    return db.get(id);
  }
}

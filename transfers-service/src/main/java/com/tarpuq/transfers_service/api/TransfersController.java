package com.tarpuq.transfers_service.api;

import com.tarpuq.transfers_service.dto.TransferRequest;
import com.tarpuq.transfers_service.service.TransferPublisher;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/transfers")
public class TransfersController {

  private final TransferPublisher publisher;

  public TransfersController(TransferPublisher publisher) {
    this.publisher = publisher;
  }

  @PostMapping
  public ResponseEntity<Map> create(@Valid @RequestBody TransferRequest req) {
    return ResponseEntity.ok(publisher.publish(req));
  }
}

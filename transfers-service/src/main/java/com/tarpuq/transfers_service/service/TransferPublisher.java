package com.tarpuq.transfers_service.service;

import com.tarpuq.transfers_service.dto.TransferRequest;
import com.tarpuq.transfers_service.mq.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class TransferPublisher {

  private final RabbitTemplate rabbit;

  public TransferPublisher(RabbitTemplate rabbit) {
    this.rabbit = rabbit;
  }

  public Map publish(TransferRequest req) {
    String transferId = UUID.randomUUID().toString();

    Map<String, Object> msg = Map.of(
        "transferId", transferId,
        "itemCode", req.itemCode(),
        "quantity", req.quantity(),
        "fromWarehouse", req.fromWarehouse(),
        "toWarehouse", req.toWarehouse()
    );

    rabbit.convertAndSend(RabbitConfig.EXCHANGE, "transfer.process", msg);
    return Map.of("ok", true, "transferId", transferId, "status", "QUEUED");
  }
}
package com.tarpuq.transfers_service.service;

import com.tarpuq.transfers_service.inventory.InventoryClient;
import com.tarpuq.transfers_service.mq.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransferConsumer {

  private final InventoryClient inventoryClient;

  public TransferConsumer(InventoryClient inventoryClient) {
    this.inventoryClient = inventoryClient;
  }

  @RabbitListener(queues = RabbitConfig.Q_PROCESS)
  public void handle(Map<String, Object> msg) {
    String itemCode = (String) msg.get("itemCode");
    int qty = (int) msg.get("quantity");

    // Si inventory falla (error 400 o servicio caído), lanzamos excepción:
    // Rabbit enviará el mensaje a DLQ por la configuración de dead-letter.
    inventoryClient.reserve(itemCode, qty);

    System.out.println("✅ Transfer procesada OK: " + msg);
  }
}
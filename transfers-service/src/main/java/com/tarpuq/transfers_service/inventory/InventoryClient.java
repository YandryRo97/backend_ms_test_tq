package com.tarpuq.transfers_service.inventory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class InventoryClient {

  private final RestTemplate rest = new RestTemplate();

  @Value("${inventory.base-url}")
  private String baseUrl;

  public Map reserve(String itemCode, int quantity) {
    System.out.println(">>> Calling inventory reserve at: " + baseUrl + "/inventory/reserve");
    String url = baseUrl + "/inventory/reserve";
    Map<String, Object> body = Map.of("itemCode", itemCode, "quantity", quantity);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    ResponseEntity<Map> resp = rest.exchange(url, HttpMethod.POST, new HttpEntity<>(body, headers), Map.class);
    return resp.getBody();
  }
}
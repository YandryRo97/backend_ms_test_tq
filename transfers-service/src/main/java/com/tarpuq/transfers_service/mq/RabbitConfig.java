package com.tarpuq.transfers_service.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  public static final String EXCHANGE = "tarpuq.transfers";
  public static final String Q_PROCESS = "transfer.process";
  public static final String Q_PROCESS_DLX = "transfer.process.dlx";
  public static final String Q_PROCESS_DLQ = "transfer.process.dlq";

  @Bean
  DirectExchange exchange() {
    return new DirectExchange(EXCHANGE);
  }

  // Cola principal: si falla, manda a DLX
  @Bean
  Queue processQueue() {
    return QueueBuilder.durable(Q_PROCESS)
        .withArgument("x-dead-letter-exchange", EXCHANGE)
        .withArgument("x-dead-letter-routing-key", "transfer.process.dlq")
        .build();
  }

  @Bean
  Queue dlq() {
    return QueueBuilder.durable(Q_PROCESS_DLQ).build();
  }

  @Bean
  Binding processBinding(Queue processQueue, DirectExchange exchange) {
    return BindingBuilder.bind(processQueue).to(exchange).with("transfer.process");
  }

  @Bean
  Binding dlqBinding(Queue dlq, DirectExchange exchange) {
    return BindingBuilder.bind(dlq).to(exchange).with("transfer.process.dlq");
  }
}

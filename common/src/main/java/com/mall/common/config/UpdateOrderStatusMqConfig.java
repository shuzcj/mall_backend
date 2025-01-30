package com.mall.common.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateOrderStatusMqConfig {

    @Bean
    Queue queue() {

        return new Queue("updateOrderStatus", true);
    }

    @Bean
    DirectExchange exchange() {

        return ExchangeBuilder.directExchange("order").build();
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {

        return BindingBuilder.bind(queue).to(exchange).with("status");
    }

}

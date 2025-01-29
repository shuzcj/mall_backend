package com.mall.common.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadLetterExchangeMqConfig {
    //only use one exchange,the routing key will change when it expired in the delay queue.

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange("dlx.exchange");
    }

    @Bean
    Queue dlQueue() {
        return QueueBuilder.durable("deadLetter.queue")
                .build();
    }

    @Bean
    Binding DLQbinding(@Qualifier("dlQueue") Queue dlQueue,
                       @Qualifier("deadLetterExchange") DirectExchange deadLetterExchange) {
        System.out.println("Binding deadLetter.queue to dlx.exchange with deadLetter");
        return BindingBuilder.bind(dlQueue).to(deadLetterExchange).with("deadLetter");
    }

    @Bean
    Queue delayQueue() {
        return QueueBuilder.durable("delay.queue")
                .withArgument("x-dead-letter-exchange", "dlx.exchange")
                .withArgument("x-dead-letter-routing-key", "deadLetter")
                .withArgument("x-message-ttl", 15000)
                .build();
    }

    @Bean
    Binding Delaybinding(@Qualifier("delayQueue") Queue delayQueue,
                    @Qualifier("deadLetterExchange") DirectExchange deadLetterExchange) {
        System.out.println("Binding delay.queue to dlx.exchange with delay");
        return BindingBuilder.bind(delayQueue).to(deadLetterExchange).with("delay");
    }
}

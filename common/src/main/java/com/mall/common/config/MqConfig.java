package com.mall.common.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    @Bean
    public MessageConverter messageConverter(){
        // 1.定义消息转换器
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        // 2.配置自动创建消息id，用于识别不同消息，也可以在业务中基于ID判断是否是重复消息
        jackson2JsonMessageConverter.setCreateMessageIds(true);
        return jackson2JsonMessageConverter;
    }

    @Bean(name = "queue1")
    Queue queue() {
        //System.out.println("Creating durable queue: test.q3");
        return new Queue("test.q3", true); // true for making the queue durable
    }

    @Bean(name = "exchange1")
    DirectExchange exchange() {
        //System.out.println("Creating direct exchange: test.direct1");
        return ExchangeBuilder.directExchange("test.direct1").build();
    }

    @Bean(name = "binding1")
    Binding binding(Queue queue, DirectExchange exchange) {
        System.out.println("Binding queue test.q3 to exchange test.direct1 with routing key red and yellow");
        return BindingBuilder.bind(queue).to(exchange).with("redddddd");
    }

}

package com.example.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigRabbitMq {
    private static final boolean NON_DURABLE = false;
    private static final String MY_QUEUE_NAME = "myQueue";
    @Bean
    public ApplicationRunner runner(RabbitTemplate template) {
        return args -> {
            template.convertAndSend("myQueue", "Hello, world!");
        };
    }

    @Bean
    public Queue myQueue() {
        return new Queue(MY_QUEUE_NAME, NON_DURABLE);
    }

    @RabbitListener(queues = MY_QUEUE_NAME)
    public void listen(String in) {
        System.out.println("Message read from myQueue : " + in);
    }
}

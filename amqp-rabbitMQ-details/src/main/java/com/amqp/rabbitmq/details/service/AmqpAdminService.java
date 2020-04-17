package com.amqp.rabbitmq.details.service;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AmqpAdminService {


    @Autowired
    private AmqpAdmin amqpAdmin;

    public void declareExchangeQueueBinding(String exchangeName, String queueName, String routeKey) {
        Exchange exchange = ExchangeBuilder.topicExchange(exchangeName)
                .durable(false)
                .build();
        amqpAdmin.declareExchange(exchange);
        Queue queue = QueueBuilder.nonDurable(queueName).build();
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(routeKey).noargs());
//        amqpAdmin.declareBinding(new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, routeKey, new HashMap<>()));
    }

    public void queuePurge(String queueName) {
        amqpAdmin.purgeQueue(queueName, false);
    }

}

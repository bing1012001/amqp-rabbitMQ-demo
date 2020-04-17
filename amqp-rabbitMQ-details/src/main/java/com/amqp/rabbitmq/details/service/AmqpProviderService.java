package com.amqp.rabbitmq.details.service;

import com.amqp.rabbitmq.details.entity.Book;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class AmqpProviderService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessageWithConfirm(String exchangeName, String routeKey, Book book) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(exchangeName, routeKey, book, correlationData);
    }
}

package com.amqp.rabbitmq.details.service;

import com.amqp.rabbitmq.details.entity.Book;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
public class AmqpConsumerService {

    @RabbitListener(queues = "test.amqp.topic.emp")
//    @RabbitHandler
    public void listenAndReceiveBook(@Payload Book book, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        log.info("---------{}", book.toString());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        log.info("------------delivery tag {}", deliveryTag);
        channel.basicAck(deliveryTag, false);
    }

}

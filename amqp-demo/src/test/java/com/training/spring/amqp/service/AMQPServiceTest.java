package com.training.spring.amqp.service;

import com.training.spring.amqp.AmqpDemoApplication;
import com.training.spring.amqp.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AmqpDemoApplication.class})
public class AMQPServiceTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Test
    public void sendMsgTest1() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "this is the first test msg");
        map.put("data", Arrays.asList("data1","data2","data3"));
        rabbitTemplate.convertAndSend("exchange.test.direct", "demo.test.news", map);
    }


    @Test
    public void sendMsgTest2() {
        Map<String, Object> map = new HashMap<>();
        Book book = Book.builder().id("12345").name("who knows").author("I don't know").build();
        rabbitTemplate.convertAndSend("exchange.test.direct", "demo.test.news", book);
    }

    @Test
    public void receiveMsgTest1() {
        Object object = rabbitTemplate.receiveAndConvert("demo.test.news");
        if(object instanceof HashMap) {
            ((HashMap<String, Object>)object).entrySet().stream()
                                                        .forEach(x -> System.out.println(x));
        }
    }
    @Test
    public void receiveMsgTest2() {
        Object object = rabbitTemplate.receiveAndConvert("demo.test.news");
        if(object instanceof Book) {
            Book book = (Book)object;
            log.info(book.toString());
        }
    }

    @Test
    public void amqpAdminTest() {
        Exchange exchange = ExchangeBuilder.topicExchange("create.admin.topic1").build();
        amqpAdmin.declareExchange(exchange);
        Queue queue = QueueBuilder.durable("admin.topic1").build();
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("*.admin.*").noargs());
        log.info("Exchange of Topic created successfully.");
    }
}

package com.amqp.rabbitmq.details.service;

import com.amqp.rabbitmq.details.entity.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmqpAdminServiceTest {

    @Autowired
    private AmqpAdminService amqpAdminService;

    @Autowired
    private AmqpProviderService amqpProviderService;

    String exchangeName = "test.amqp.topic.exchange";
    String queueName = "test.amqp.topic.emp";
    String routeKey = "amqp.emp.#";

    @Before
    public void setup() {
        Assert.assertNotNull(amqpAdminService);
    }

    @Test
    public void declareBindingTest() {


        amqpAdminService.declareExchangeQueueBinding(exchangeName, queueName, routeKey);
    }

    @Test
    public void sendAndReceive() {
        for(int i = 0; i<10;i++) {
            Book book  = Book.builder().id(UUID.randomUUID().toString()).author("ABC").name("abc").build();
            amqpProviderService.sendMessageWithConfirm(exchangeName, "amqp.emp.book", book);
        }
    }

}

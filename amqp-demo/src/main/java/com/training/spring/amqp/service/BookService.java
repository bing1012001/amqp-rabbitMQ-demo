package com.training.spring.amqp.service;

import com.training.spring.amqp.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
@Slf4j
@Service
public class BookService {

    @RabbitListener(queues = "demo.test.news")
    public void listenAndReceive(Book book) {

        log.info(String.format("Receive new MSG: %s has been received", book.getName()));
    }

}

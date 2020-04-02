package com.payment.transfer.module.service;

import com.payment.transfer.module.entity.TransferRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchange, String routeKey, TransferRecord transferRecord) {
        log.info("============== payment transfer {} delivering, please wait", transferRecord);
        rabbitTemplate.convertAndSend(exchange, routeKey, transferRecord);
    }

}

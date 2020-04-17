package com.amqp.rabbitmq.details.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Slf4j
@Configuration
public class AmqpConfig {

    @Resource
    private ConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate getRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        rabbitTemplate.setMandatory(true); //customized rabbitTemplate cannot load the yml config of mandatory
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("CorrelationData is {}",correlationData);
            if(ack) {
                log.info("ACK is {}", ack); //normally we can do db update
            } else {
                log.error("ACK is not positive, exception should be handled");
            }
        });

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.error("Return call back with Exchange {} routingKey {} reply code {} ", exchange, routingKey, replyCode);
        });
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}

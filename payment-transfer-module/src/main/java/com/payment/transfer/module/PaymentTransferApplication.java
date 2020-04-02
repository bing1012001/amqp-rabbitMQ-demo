package com.payment.transfer.module;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.payment.transfer.module.mapper")
public class PaymentTransferApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentTransferApplication.class, args);
    }
}

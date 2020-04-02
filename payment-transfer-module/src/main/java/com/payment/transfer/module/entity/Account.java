package com.payment.transfer.module.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@ToString
public class Account implements Serializable {
    private String userId;

    private Integer amount;

    private Timestamp updateTime;

}

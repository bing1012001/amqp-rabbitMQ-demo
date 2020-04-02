package com.payment.transfer.module.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class TransferRecord implements Serializable {

    private String messageId;

    private String userId;

    private Integer amount;

    private String status;

}

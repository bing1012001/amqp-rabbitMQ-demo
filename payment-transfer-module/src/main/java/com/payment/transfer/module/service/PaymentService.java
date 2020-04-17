package com.payment.transfer.module.service;

import com.payment.transfer.module.entity.Account;
import com.payment.transfer.module.exception.InsufficientFundException;
import com.payment.transfer.module.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Service
public class PaymentService {

    @Resource
    private AccountMapper accountMapper;

    public void makePaymentByUserId(String userId, int amount) {
        Account account = accountMapper.getAccountInfo(userId);
        if(account.getAmount() < amount) {
            throw new InsufficientFundException(String.format("Insufficient Fund, cannot make the payment."));
        }
        account.setAmount(amount);
        account.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        accountMapper.updateAccount(account);
    }

    public void createTransferRecord(String userId, int amount) {

    }
}

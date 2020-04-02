package com.payment.transfer.module.mapper;

import com.payment.transfer.module.entity.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.Times;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest {

    @Resource
    private AccountMapper accountMapper;

    @Before
    public void setup() {
        Assert.assertNotNull(accountMapper);
    }

    @Test
    public void accountAddTest() {
        Account account = Account.builder().userId(UUID.randomUUID().toString()).amount(1000).updateTime(Timestamp.valueOf(LocalDateTime.now())).build();
        accountMapper.AddNewAccount(account);
    }

    @Test
    public void getAccountInfoTest() {
        Account account = accountMapper.getAccountInfo("c0eb0353-5477-44ed-8cde-4bd56552e411");
        Assert.assertNotNull(account);
        Assert.assertEquals(1000, (long)account.getAmount());
    }

}

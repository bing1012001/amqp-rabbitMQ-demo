package com.payment.transfer.module.mapper;

import com.payment.transfer.module.entity.Account;
import com.payment.transfer.module.entity.TransferRecord;
import org.apache.ibatis.annotations.Mapper;
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

    @Resource
    private TransferRecordMapper transferRecordMapper;

    @Before
    public void setup() {
        Assert.assertNotNull(accountMapper);
        Assert.assertNotNull(transferRecordMapper);
    }

    @Test
    public void accountAddTest() {
        Account account = Account.builder().userId(UUID.randomUUID().toString()).amount(1200).updateTime(Timestamp.valueOf(LocalDateTime.now())).build();
        int i = accountMapper.AddNewAccount(account);
        Assert.assertEquals(1, i);


    }

    @Test
    public void getAccountInfoTest() {
        Account account = accountMapper.getAccountInfo("c0eb0353-5477-44ed-8cde-4bd56552e411");
        Assert.assertNotNull(account);
        Assert.assertEquals(1000, (long)account.getAmount());
    }

    @Test
    public void transferRecordTest() {
        String messageID = UUID.randomUUID().toString();
        TransferRecord transferRecord = TransferRecord.builder()
                .messageId(messageID)
                .userId("c0eb0353-5477-44ed-8cde-4bd56552e411")
                .status("PENDING")
                .amount(100)
                .build();
        transferRecordMapper.addNewTransferRecord(transferRecord);
        TransferRecord transferRecord1 = transferRecordMapper.findTransferRecord(messageID);
        transferRecord1.setStatus("CONFIRMED");
        transferRecordMapper.updateTransferRecord(transferRecord1);

    }

}

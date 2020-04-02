package com.payment.transfer.module.mapper;

import com.payment.transfer.module.entity.TransferRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TransferRecordMapper {

    @Select("SELECT * FROM transfer_record WHERE message_id = #{messageId}")
    public TransferRecord findTransferRecord(String messageId);

    @Insert("INSERT INTO transfer_record (message_id, user_id, amount, status) VALUES (#{messageId}, #{userId}, #{amount}, #{status})")
    public void addNewTransferRecord(TransferRecord transferRecord);

    @Update("UPDATE transfer_record SET status = #{status} WHERE message_id = #{messageId}")
    public void updateTransferRecord(TransferRecord transferRecord);

    @Select("SELECT * FROM transfer_record WHERE status = #{status}")
    public List<TransferRecord> findTransferRecordsByStatus(String status);

}

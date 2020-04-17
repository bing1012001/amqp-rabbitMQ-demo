package com.payment.transfer.module.mapper;

import com.payment.transfer.module.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper {

    @Update("UPDATE  account SET amount = #{amount}, update_time = #{updateTime}")
    public int updateAccount(Account account);

    @Insert("INSERT INTO account (user_id, amount, update_time) VALUES (#{userId}, #{amount}, #{updateTime})")
    public int AddNewAccount(Account account);

    @Select("SELECT * FROM account WHERE user_id = #{userId}")
    public Account getAccountInfo(String userId);

}

package com.unluckyworker.appointment.dao;

import com.unluckyworker.appointment.pojo.Login;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginMapper {

    List<Login> queryLoginList();

    Login queryLoginById(int id);

    int addLogin(Login login);

    int updateLogin(Login login);

    int deleteLoginById(int id);
}

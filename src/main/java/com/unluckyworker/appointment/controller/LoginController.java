package com.unluckyworker.appointment.controller;

import com.unluckyworker.appointment.dao.LoginMapper;
import com.unluckyworker.appointment.pojo.Login;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private LoginMapper loginMapper;

    @GetMapping("/queryLoginList")
    @ApiOperation("查询所有登陆账号")
    public List<Login> queryLoginList() {
        List<Login> logins = loginMapper.queryLoginList();
        return logins;
    }

    @GetMapping("/queryLoginById")
    @ApiOperation("根据id查询账号")
    public Login queryLoginById(@ApiParam("登陆账号id") int id) {
        Login login = loginMapper.queryLoginById(id);
        return login;
    }

    @GetMapping("/addLogin")
    @ApiOperation("添加一个登陆账号")
    public int addLogin(@ApiParam("登陆账号") Login login) {
        int i = loginMapper.addLogin(login);
        return i;
    }

    @GetMapping("/updateLogin")
    @ApiOperation("更新一个登陆账号")
    public int updateLogin(@ApiParam("登陆账号") Login login) {
        int i = loginMapper.updateLogin(login);
        return i;
    }

    @GetMapping("/deleteLoginById")
    @ApiOperation("删除一个登陆账号")
    public int deleteLoginById(@ApiParam("登陆账号id") int id) {
        int i = loginMapper.deleteLoginById(id);
        return i;
    }

}

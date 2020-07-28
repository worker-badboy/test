package com.unluckyworker.appointment.controller;

import com.alibaba.fastjson.JSONObject;
import com.unluckyworker.appointment.dao.LoginMapper;
import com.unluckyworker.appointment.pojo.Login;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
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

    @PostMapping("/queryLoginById")
    @ApiOperation("根据id查询账号")
    @ApiImplicitParam(name = "id", value = "登陆id", dataType = "int")
    public Login queryLoginById(int id) {

        Login login = loginMapper.queryLoginById(id);
        return login;
    }

    @PostMapping("/addLogin")
    @ApiOperation("添加一个登陆账号")
    public int addLogin(Login login) {
        int i = loginMapper.addLogin(login);
        return i;
    }

    @PostMapping("/updateLogin")
    @ApiOperation("更新一个登陆账号")
    public int updateLogin(Login login) {
        int i = loginMapper.updateLogin(login);
        return i;
    }

    @PostMapping("/deleteLoginById")
    @ApiOperation("删除一个登陆账号")
    @ApiImplicitParam(name = "id", value = "登陆id", dataType = "int", required = true)
    public int deleteLoginById(int id) {
        int i = loginMapper.deleteLoginById(id);
        return i;
    }

    @PostMapping("/queryLogin")
    @ApiOperation("验证登陆")
    public boolean queryLogin(Login login){
        String password = loginMapper.queryLogin(login);
        return password.equals(login.getPassword());
    }

}

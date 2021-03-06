package com.unluckyworker.appointment.controller;

import com.alibaba.fastjson.JSONObject;
import com.unluckyworker.appointment.dao.LoginMapper;
import com.unluckyworker.appointment.pojo.Login;
import com.unluckyworker.appointment.utils.JWTUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {

    @Autowired
    private LoginMapper loginMapper;

    @GetMapping("/admin/queryLoginList")
    @ApiOperation("查询所有登陆账号")
    public List<Login> queryLoginList() {
        List<Login> logins = loginMapper.queryLoginList();
        return logins;
    }

    @PostMapping("/admin/queryLoginById")
    @ApiOperation("根据id查询账号")
    @ApiImplicitParam(name = "id", value = "登陆id", dataType = "String")
    public Login queryLoginById(String id) {

        Login login = loginMapper.queryLoginById(id);
        System.out.println(JSONObject.toJSON(login));
        return login;
    }

    @PostMapping("/admin/addLogin")
    @ApiOperation("添加一个登陆账号")
    public int addLogin(Login login) {
        int i = loginMapper.addLogin(login);
        return i;
    }

    @PostMapping("/admin/updateLogin")
    @ApiOperation("更新一个登陆账号")
    public int updateLogin(Login login) {
        int i = loginMapper.updateLogin(login);
        return i;
    }

    @PostMapping("/admin/deleteLoginById")
    @ApiOperation("删除一个登陆账号")
    @ApiImplicitParam(name = "id", value = "登陆id", dataType = "String", required = true)
    public int deleteLoginById(String id) {
        int i = loginMapper.deleteLoginById(id);
        return i;
    }

    @PostMapping("/admin/queryLogin")
    @ApiOperation("验证登陆")
    public String queryLogin(@RequestBody JSONObject json){
        Map<String, Object> res = new HashMap<>();
        try {
            String user_password = json.get("password").toString();
            String user_id = json.get("id").toString();
            String password = loginMapper.queryLogin(new Login(user_id,user_password));
            if(password.equals(user_password)){
                Map<String, String> map = new HashMap<>();
                map.put("用户id",String.valueOf(user_id));
                String token = JWTUtils.getToken(map);
                res.put("code",0);
                res.put("data",new HashMap<String, String>(){{put("adminToken",token);}});
            }else {
                res.put("code",1);
            }
        }catch (Exception e){
            res.put("code",1);
        }
        log.info("");
        return JSONObject.toJSONString(res);
}

}

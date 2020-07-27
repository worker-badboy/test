package com.unluckyworker.appointment.controller;

import com.unluckyworker.appointment.dao.VisitMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class VisitController {

    @Autowired
    private VisitMapper visitMapper;

    @PostMapping("/queryVisitById")
    @ApiOperation("根据id获取医生上班信息")
    @ApiImplicitParam(name = "did",value = "医生工号",dataType = "int")
    public List<Map<String,Object>> queryVisitById(int did){
        List<Map<String, Object>> maps = visitMapper.queryVisitById(did);
        return maps;
    }
}

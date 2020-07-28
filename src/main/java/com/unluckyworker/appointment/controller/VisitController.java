package com.unluckyworker.appointment.controller;

import com.unluckyworker.appointment.dao.VisitMapper;
import com.unluckyworker.appointment.pojo.Visit;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
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

    @PostMapping("/queryVisitByVisitdate")
    @ApiOperation("根据日期获取医生上班信息")
    public List<Map<String,Object>> queryVisitByVisitdate(Date visitdate){
        List<Map<String, Object>> maps = visitMapper.queryVisitByVisitdate(visitdate);
        return maps;
    }

    @GetMapping("/queryVisit")
    @ApiOperation("获取全部医生上班信息")
    public List<Map<String,Object>> queryVisit(){
        List<Map<String, Object>> maps = visitMapper.queryVisit();
        return maps;
    }

    @PostMapping("/addVisit")
    @ApiOperation("增加某个医生可预约时间")
    public int addVisit(Visit visit){
        int i = visitMapper.addVisit(visit);
        return i;
    }

    @PostMapping("/deleteVisit")
    @ApiOperation("删除某个医生可预约时间")
    public int deleteVisit(Visit visit){
        int i = visitMapper.deleteVisit(visit);
        return i;
    }

    @PostMapping("/updateFlag")
    @ApiOperation("修改医生是否预约")
    public int updateFlag(Visit visit){
        int i = visitMapper.updateFlag(visit);
        return i;
    }

    @PostMapping("/updateVisit")
    @ApiOperation("更新医生可预约时间")
    public int updateVisit(int did , Date visitdate , String visittime ,  String newtime){
        int i = visitMapper.updateVisit(did,visitdate,visittime, newtime);
        return i;
    }

}

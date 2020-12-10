package com.unluckyworker.appointment.controller;

import com.alibaba.fastjson.JSONObject;
import com.unluckyworker.appointment.dao.DoctorMapper;
import com.unluckyworker.appointment.dao.VisitMapper;
import com.unluckyworker.appointment.pojo.Doctor;
import com.unluckyworker.appointment.pojo.Visit;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class VisitController {

    @Autowired
    private VisitMapper visitMapper;


    @PostMapping("/queryVisitById")
    @ApiOperation("根据id获取医生上班信息")
    @ApiImplicitParam(name = "did", value = "医生工号", dataType = "int")
    public List<Map<String, Object>> queryVisitById(int did) {
        List<Map<String, Object>> maps = visitMapper.queryVisitById(did);
        return maps;
    }

    @PostMapping("/queryVisitByVisitdate")
    @ApiOperation("根据日期获取医生上班信息")
    public List<Map<String, Object>> queryVisitByVisitdate(Date visitdate) {
        List<Map<String, Object>> maps = visitMapper.queryVisitByVisitdate(visitdate);
        return maps;
    }

    @GetMapping("/queryVisit")
    @ApiOperation("获取全部医生上班信息")
    public List<Map<String, Object>> queryVisit() {
        List<Map<String, Object>> maps = visitMapper.queryVisit();
        return maps;
    }

    @PostMapping("/addVisit")
    @ApiOperation("增加某个医生可预约时间")
    public int addVisit(Visit visit) {
        int i = visitMapper.addVisit(visit);
        return i;
    }

    @PostMapping("/deleteVisit")
    @ApiOperation("删除某个医生可预约时间")
    public int deleteVisit(Visit visit) {
        int i = visitMapper.deleteVisit(visit);
        return i;
    }

    @PostMapping("/updateFlag")
    @ApiOperation("修改医生是否预约")
    public int updateFlag(Visit visit) {
        int i = visitMapper.updateFlag(visit);
        return i;
    }

    @PostMapping("/updateVisit")
    @ApiOperation("更新医生可预约时间")
    public int updateVisit(int did, Date visitdate, String visittime, String newtime) {
        int i = visitMapper.updateVisit(did, visitdate, visittime, newtime);
        return i;
    }

    @PostMapping("/updateOneVisit")
    public String updateOneVisit(@RequestBody Visit visit) {
        Map<String, Integer> res = new HashMap<>();
        try {
            int i = visitMapper.updateOneVisit(visit);
            if (i == 1) {
                res.put("code", 0);
            } else {
                res.put("code", 1);
            }
        } catch (Exception e) {
            res.put("code", 1);
        }
        return JSONObject.toJSONString(res);
    }

    @PostMapping("/getNext7DaysVisit")
    public String getNext7DaysVisit(@RequestBody JSONObject json) {
        Map<String, Object> res = new HashMap<>();
        try {
            int did = json.getInteger("did");
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String fromDate = format.format(calendar.getTime());
            calendar.add(Calendar.DATE, 6);
            String toDate = format.format(calendar.getTime());

            List<Visit> visits = visitMapper.getNext7DaysVisit(did, fromDate, toDate);
            List<Object> list = new ArrayList<>();
            for (Visit visit : visits) {
                list.add(JSONObject.toJSON(visit));
            }
            res.put("code", 0);
            res.put("data", list);
        } catch (Exception e) {
            res.put("code", 1);
        }
        return JSONObject.toJSONString(res);
    }

    @PostMapping("/getAfterNextVisit")
    public String getAfterNextVisit(@RequestBody JSONObject json) {
        Map<String, Object> res = new HashMap<>();
        try {
            int did = json.getInteger("did");
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            switch (weekday) {
                case 0:
                    calendar.add(Calendar.DATE, 8);
                    break;
                case 1:
                    calendar.add(Calendar.DATE, 14);
                    break;
                case 2:
                    calendar.add(Calendar.DATE, 13);
                    break;
                case 3:
                    calendar.add(Calendar.DATE, 12);
                    break;
                case 4:
                    calendar.add(Calendar.DATE, 11);
                    break;
                case 5:
                    calendar.add(Calendar.DATE, 10);
                    break;
                case 6:
                    calendar.add(Calendar.DATE, 9);
                    break;
                default:
                    ;
            }
            String fromDate = format.format(calendar.getTime());
            calendar.add(Calendar.DATE, 6);
            String toDate = format.format(calendar.getTime());

            List<Visit> visits = visitMapper.getNext7DaysVisit(did, fromDate, toDate);
            List<Object> list = new ArrayList<>();
            for (Visit visit : visits) {
                list.add(JSONObject.toJSON(visit));
            }
            res.put("code", 0);
            res.put("data", list);
        } catch (Exception e) {
            res.put("code", 1);
        }
        return JSONObject.toJSONString(res);

    }
}

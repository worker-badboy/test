package com.unluckyworker.appointment.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unluckyworker.appointment.dao.DoctorMapper;
import com.unluckyworker.appointment.pojo.Doctor;
import com.unluckyworker.appointment.pojo.Login;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RequestBody @Controller
@RestController //json
@CrossOrigin(origins = "*", maxAge = 3600)
public class DoctorController {

    @Autowired
    private DoctorMapper doctorMapper;

    @GetMapping("/queryDoctorList")
    @ApiOperation("查询所有医生信息")
    public String queryDoctorList() {
        Map<String, Object> res = new HashMap<>();
        try{
            List<Doctor> doctors = doctorMapper.queryDoctorList();
            List<Object> list = new ArrayList<>();
            for (Doctor doctor : doctors) {
                list.add(JSONObject.toJSON(doctor));
            }
            res.put("code",0);
            res.put("data",list);
        }catch (Exception e){
            res.put("code",1);
        }
        return JSONObject.toJSONString(res);
    }
    //接收的参数就是前端传进来的数据, 我们的返回值就是传给前端的数据
    @PostMapping("/queryDoctorById")
    @ApiOperation("根据医生id查询医生信息")
    public Doctor queryDoctorById(int did) {
        Doctor doctor = doctorMapper.queryDoctorById(did);
        return doctor;
    }

    @PostMapping("/addDoctor")
    @ApiOperation("增加医生")
    public String addDoctor(@RequestBody Doctor doctor) {
        Map<String,Integer> map = new HashMap<>();
        try{
            doctorMapper.addDoctor(doctor);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
        }
        return JSONObject.toJSONString(map);
    }

    @PostMapping("/updateDoctor")
    @ApiOperation("修改医生信息")
    public String updateDoctor(@RequestBody Doctor doctor) {
        Map<String, Integer> map = new HashMap<>();
        try {
            Doctor test = doctorMapper.queryDoctorById(doctor.getDid());
            doctor.setDid(test.getDid());
           doctorMapper.updateDoctor(doctor);
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
        }
        return JSONObject.toJSONString(map);
    }

    @PostMapping("/deleteDoctorById")
    @ApiOperation("根据医生id删除医生")
    public String deleteDoctorById(@RequestBody JSONObject json) {
        Map<String, Integer> map = new HashMap<>();
        try {
            Doctor doctor = doctorMapper.queryDoctorById(json.getInteger("did"));
            doctorMapper.deleteDoctorById(doctor.getDid());
            map.put("code",0);
        }catch (Exception e){
            map.put("code",1);
        }
        return JSONObject.toJSONString(map);
    }

    @GetMapping("/queryHospital")
    @ApiOperation("查询所有医院")
    public List<String> queryHospital() {
        List<String> hospital = doctorMapper.queryHospital();
        return hospital;
    }

    @PostMapping("/queryDepartment")
    @ApiOperation("查询某个医院的所有科室")
    @ApiImplicitParam(name = "hospital", value = "医院名称", dataType = "string")
    public List<String> queryDepartment(String hospital) {
        List<String> department = doctorMapper.queryDepartment(hospital);
        return department;
    }

    @PostMapping("/queryDoctorByDepartment")
    @ApiOperation("根据医院和科室查询医生信息")
    public String queryDoctorByDepartment(@RequestBody JSONObject json) {
        Map<String, Object> res = new HashMap<>();
        try {
            String hospital = json.getString("hospital");
            String department = json.getString("department");
            List<Doctor> doctors = doctorMapper.queryDoctorByDepartment(hospital, department);
            List<Object> list = new ArrayList<>();
            for (Doctor doctor : doctors) {
                list.add(JSONObject.toJSON(doctor));
            }
            res.put("code",0);
            res.put("data",list);
        }catch (Exception e){
            res.put("code",1);
        }
        return JSONObject.toJSONString(res);
    }

}

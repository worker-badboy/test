package com.unluckyworker.appointment.controller;

import com.unluckyworker.appointment.dao.DoctorMapper;
import com.unluckyworker.appointment.pojo.Doctor;
import com.unluckyworker.appointment.pojo.Login;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    private DoctorMapper doctorMapper;

    @GetMapping("/queryDoctorList")
    @ApiOperation("查询所有医生信息")
    public List<Doctor> queryDoctorList(){
        List<Doctor> doctors = doctorMapper.queryDoctorList();
        return doctors;
    }

    @GetMapping("/queryDoctorById")
    @ApiOperation("根据医生id查询医生信息")
    public Doctor queryDoctorById(@ApiParam("医生id") int did){
        Doctor doctor = doctorMapper.queryDoctorById(did);
        return doctor;
    }

    @GetMapping("/addDoctor")
    @ApiOperation("增加医生")
    public int addDoctor(Doctor doctor){
        int i = doctorMapper.addDoctor(doctor);
        return i;
    }

    @GetMapping("/updateDoctor")
    @ApiOperation("修改医生信息")
    public int updateDoctor(Doctor doctor){
        int i = doctorMapper.updateDoctor(doctor);
        return i;
    }

    @GetMapping("/deleteDoctorById")
    @ApiOperation("根据医生id删除医生")
    public int deleteDoctorById(@ApiParam("医生id") int did){
        int i = doctorMapper.deleteDoctorById(did);
        return i;
    }

    @GetMapping("/queryHospital")
    @ApiOperation("查询所有医院")
    public List<String> queryHospital(){
        List<String> hospital = doctorMapper.queryHospital();
        return hospital;
    }

    @GetMapping("/queryDepartment")
    @ApiOperation("查询某个医院的所有科室")
    public List<String> queryDepartment(@ApiParam("医院名称") String hospital){
        List<String> department = doctorMapper.queryDepartment(hospital);
        return department;
    }

    @GetMapping("/queryDoctorByDepartment")
    @ApiOperation("根据医院和科室查询医生信息")
    public List<Doctor> queryDoctorByDepartment(@ApiParam("医院名称") String hospital,@ApiParam("科室名称") String department){
        List<Doctor> doctors = doctorMapper.queryDoctorByDepartment(hospital, department);
        return doctors;
    }

}

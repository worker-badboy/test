package com.unluckyworker.appointment.controller;

import com.unluckyworker.appointment.dao.DoctorMapper;
import com.unluckyworker.appointment.pojo.Doctor;
import com.unluckyworker.appointment.pojo.Login;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    private DoctorMapper doctorMapper;

    @GetMapping("/queryDoctorList")
    @ApiOperation("查询所有医生信息")
    public List<Doctor> queryDoctorList() {
        List<Doctor> doctors = doctorMapper.queryDoctorList();
        return doctors;
    }

    @PostMapping("/queryDoctorById")
    @ApiOperation("根据医生id查询医生信息")
    @ApiImplicitParam(name = "did", value = "医生工号", dataType = "int")
    public Doctor queryDoctorById(int did) {
        Doctor doctor = doctorMapper.queryDoctorById(did);
        return doctor;
    }

    @PostMapping("/addDoctor")
    @ApiOperation("增加医生")
    public int addDoctor(Doctor doctor) {
        int i = doctorMapper.addDoctor(doctor);
        return i;
    }

    @PostMapping("/updateDoctor")
    @ApiOperation("修改医生信息")
    public int updateDoctor(Doctor doctor) {
        int i = doctorMapper.updateDoctor(doctor);
        return i;
    }

    @PostMapping("/deleteDoctorById")
    @ApiOperation("根据医生id删除医生")
    @ApiImplicitParam(name = "did", value = "医生工号", dataType = "int")
    public int deleteDoctorById(int did) {
        int i = doctorMapper.deleteDoctorById(did);
        return i;
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
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "hospital", value = "医院名称", dataType = "string"),
                    @ApiImplicitParam(name = "department", value = "科室", dataType = "string")
            }
    )
    public List<Doctor> queryDoctorByDepartment(String hospital, String department) {
        List<Doctor> doctors = doctorMapper.queryDoctorByDepartment(hospital, department);
        return doctors;
    }

}

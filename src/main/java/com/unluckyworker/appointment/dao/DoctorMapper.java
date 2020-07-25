package com.unluckyworker.appointment.dao;

import com.unluckyworker.appointment.pojo.Doctor;
import com.unluckyworker.appointment.pojo.Login;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DoctorMapper {

    List<Doctor> queryDoctorList();

    Doctor queryDoctorById(int did);

    int addDoctor(Doctor doctor);

    int updateDoctor(Doctor doctor);

    int deleteDoctorById(int did);

    List<String> queryHospital();

    List<String> queryDepartment(String hospital);

    List<Doctor> queryDoctorByDepartment(String hospital,String department);

}

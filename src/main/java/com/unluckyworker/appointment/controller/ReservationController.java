package com.unluckyworker.appointment.controller;

import com.unluckyworker.appointment.dao.ReservationMapper;
import com.unluckyworker.appointment.pojo.Reservation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReservationController {

    @Autowired
    private ReservationMapper reservationMapper;

    @PostMapping("/addReservation")
    @ApiOperation("插入新预约")
    public int addReservation(Reservation reservation){
        int i = reservationMapper.addReservation(reservation);
        return i;
    }

    @PostMapping("/queryReservationByPatientId")
    @ApiOperation("根据病人ID获取其预约情况")
    public List<Reservation> queryReservationByPatientId(String pid){
        List<Reservation> Reservations = reservationMapper.queryReservationByPatientId(pid);
        return  Reservations;
    }

    @PostMapping("/queryReservationByDoctorId")
    @ApiOperation("根据医生ID获取其被预约情况")
    public List<Reservation> queryReservationByDoctorId(int did){
        List<Reservation> Reservations = reservationMapper.queryReservationByDoctorId(did);
        return  Reservations;
    }


    @GetMapping("/queryAllReservation")
    @ApiOperation("获取全部预约情况")
    public List<Reservation> queryAllReservation(){
        List<Reservation> Reservations = reservationMapper.queryAllReservation();
        return  Reservations;
    }
}

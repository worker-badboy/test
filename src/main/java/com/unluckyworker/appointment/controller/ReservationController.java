package com.unluckyworker.appointment.controller;

import com.alibaba.fastjson.JSONObject;
import com.unluckyworker.appointment.dao.ReservationMapper;
import com.unluckyworker.appointment.pojo.Reservation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReservationController {

    @Autowired
    private ReservationMapper reservationMapper;

    @PostMapping("/wx/addReservation")
    public String addReservation(@RequestBody Reservation reservation){
        int code = 1;
        try {
            reservationMapper.addReservation(reservation);
            code = 0;
        }catch (Exception e){}
        return JSONObject.toJSONString(code);
    }

    @GetMapping("/wx/getReservationByOpenid")
    public String getReservationByOpenid(String openid){
        int code = 1;
        Map<String, Object> res = new HashMap<>();
        try {
            List<Reservation> list = reservationMapper.getReservationByOpenid(openid);
            List<Object> reservation = new ArrayList<>();
            for(Reservation r : list){
                reservation.add(JSONObject.toJSON(r));
            }
            res.put("code",0);
            res.put("data",reservation);
        }catch (Exception e){
            res.put("code",code);
        }
        return JSONObject.toJSONString(res);
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

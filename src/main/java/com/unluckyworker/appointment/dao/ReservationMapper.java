package com.unluckyworker.appointment.dao;

import com.unluckyworker.appointment.pojo.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReservationMapper {

    int addReservation(Reservation reservation);

    List<Reservation> queryReservationByPatientId(String pid);

    List<Reservation> queryReservationByDoctorId(int did);

    List<Reservation> queryAllReservation();
}

package com.unluckyworker.appointment.dao;

import com.unluckyworker.appointment.pojo.Visit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface VisitMapper {
    List<Map<String,Object>> queryVisitById(int did);
    List<Map<String,Object>> queryVisitByVisitdate(Date visitdate);
    List<Map<String,Object>> queryVisit();
    int addVisit(Visit visit);
    int deleteVisit(Visit visit);
    int updateFlag(Visit visit);
    int updateVisit(int did , Date visitdate , String visittime ,  String newtime);


}

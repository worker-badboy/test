package com.unluckyworker.appointment.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private int did;
    private String pid;
    private Date visitdate;
    private String visittime;
    private String paytime;
    private String pname;
    private String pphone;
    private double amount;
    private String openid;
    private String sex;
    private int age;

}

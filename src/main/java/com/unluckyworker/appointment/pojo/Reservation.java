package com.unluckyworker.appointment.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("预约信息类")
public class Reservation {
    @ApiModelProperty("医生工号")
    private int did;
    @ApiModelProperty("患者身份证号")
    private String pid;
    @ApiModelProperty("预约日期")
    private Date visitdate;
    @ApiModelProperty("预约时间")
    private String visittime;
    @ApiModelProperty("支付时间")
    private Date paytime;
    @ApiModelProperty("患者姓名")
    private String pname;
    @ApiModelProperty("患者电话")
    private String pphone;
    @ApiModelProperty("患者住址")
    private String paddress;
}

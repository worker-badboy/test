package com.unluckyworker.appointment.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("医生上班时间类")
public class Visit {
    @ApiModelProperty("医生工号")
    private int did;
    @ApiModelProperty("可预约日期")
    private Date visitdate;
    @ApiModelProperty("可预约时间")
    private String visittime;
    @ApiModelProperty("是否被预约")
    private int flag;
}

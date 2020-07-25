package com.unluckyworker.appointment.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("医生信息类")
public class Doctor {
    @ApiModelProperty("工号")
    private int did;
    @ApiModelProperty("名字")
    private String dname;
    @ApiModelProperty("职称")
    private String title;
    @ApiModelProperty("科室")
    private String department;
    @ApiModelProperty("所属医院")
    private String hospital;
    @ApiModelProperty("预约金额")
    private double price;
}

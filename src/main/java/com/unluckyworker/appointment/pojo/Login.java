package com.unluckyworker.appointment.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("登陆类")
public class Login {

    @ApiModelProperty("用户名")
    private String id;
    @ApiModelProperty("密码")
    private String password;
}

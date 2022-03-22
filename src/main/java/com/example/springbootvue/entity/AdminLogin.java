package com.example.springbootvue.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;



@Data
public class AdminLogin implements Serializable {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;

}

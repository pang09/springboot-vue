package com.example.springbootvue.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "admin")
@Data
public class Admin implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @TableId
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uid")
    private int uid;

    @Column(name = "username")
    @ApiModelProperty(value = "名字")
    private String username;

    @Column(name = "sex")
    @ApiModelProperty(value = "性别")
    private String sex;

    @Column(name = "avatar")
    @ApiModelProperty(value = "头像")
    private String avatar;

    @Column(name = "password")
    @ApiModelProperty(value = "密码")
    private String password;

    @Column(name = "tel")
    @ApiModelProperty(value = "电话")
    private String tel;

    @Column(name = "money")
    @ApiModelProperty(value = "余额")
    private int money;

    @Column(name = "location")
    @ApiModelProperty(value = "地址")
    private String location;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}

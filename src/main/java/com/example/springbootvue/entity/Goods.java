package com.example.springbootvue.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "goods")
@Data
public class Goods{

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gid")
    @ApiModelProperty(value = "gid")
    @TableId(type = IdType.AUTO)
    private Integer gid;

    @Column(name = "uid")
    @ApiModelProperty(value = "Uid")
    private int uid;

    @Column(name = "gname")
    @ApiModelProperty(value = "商品名称")
    private String gname;

    @Column(name = "price")
    @ApiModelProperty(value = "商品价格")
    private Integer price;

    @Column(name = "pic")
    @ApiModelProperty(value = "商品图片")
    private String pic;

    @Column(name = "status")
    @ApiModelProperty(value = "状态")
    private Integer status;

    @Column(name = "remark")
    @ApiModelProperty(value = "商品描述")
    private String remark;

    @Column(name = "cid")
    @ApiModelProperty(value = "分类ID")
    private Integer cid;

}

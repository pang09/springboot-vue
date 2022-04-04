package com.example.springbootvue.controller;


import com.example.springbootvue.entity.Goods;
import com.example.springbootvue.entity.R;
import com.example.springbootvue.service.GoodsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AddGoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "添加商品信息")
    @ResponseBody
    @PostMapping("/goods/addGood")
    public R addGood(@RequestBody Goods goods) {
        return goodsService.addGood(goods);
    }

    @ApiOperation(value = "修改商品信息")
    @ResponseBody
    @PostMapping("/goods/updateGood")
    public R updateGood(@RequestBody Goods goods) {
        return goodsService.updateGood(goods);
    }

    @ApiOperation(value = "删除商品信息")
    @ResponseBody
    @DeleteMapping("/goods/deleteGood/{gid}")
    public R deleteGood(@PathVariable Integer gid) {
        return goodsService.deleteGood(gid);
    }
}

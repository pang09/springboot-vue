package com.example.springbootvue.controller;

import com.example.springbootvue.entity.R;
import com.example.springbootvue.service.GoodsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "分页查询所有")
    @ResponseBody
    @GetMapping("/goods/findGoodsInfoByPage/{page}/{limit}")
    public R selectPage(@PathVariable Long page, @PathVariable Long limit){
        return goodsService.selectPage(page,limit);
    }

    @ApiOperation(value = "根据ID分页查询所有")
    @ResponseBody
    @GetMapping("/goods/findGoodsInfoByPageAndCid/{cid}/{page}/{limit}")
    public R selectPageByCid(@PathVariable Integer cid,@PathVariable Long page, @PathVariable Long limit){
        return goodsService.selectPageByCid(cid,page,limit);
    }

    @ApiOperation(value = "模糊查询所有")
    @ResponseBody
    @GetMapping("/goods/findGoodsBySelect/{name}/{page}/{limit}")
    public R findGoodsBySelect(@PathVariable String name,@PathVariable Long page, @PathVariable Long limit){
        return goodsService.findGoodsBySelect(name,page,limit);
    }

    @ApiOperation(value = "根据ID查询商品信息")
    @ResponseBody
    @GetMapping("/goods/findGoodById/{gid}")
    public R findGoodById(@PathVariable Integer gid){
        return goodsService.findGoodById(gid);
    }


}

package com.example.springbootvue.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootvue.entity.Goods;
import com.example.springbootvue.entity.R;
import com.example.springbootvue.mapper.GoodsMapper;
import com.example.springbootvue.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public R selectPage(Long page,Long limit) {
        Page<Goods> pageParam = new Page<>(page, limit);
        IPage<Goods> userIPage = goodsMapper.selectPage(pageParam, null);
        long total = pageParam.getTotal();
        long pages = pageParam.getPages();
        List<Goods> goods = userIPage.getRecords();
        return R.ok().message("查询成功").data("data",goods).data("total",total).data("pages",pages);
    }

    public R selectPageByCid(Integer cid,Long page,Long limit) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("cid",cid);
        Page<Goods> pageParam = new Page<>(page, limit);
        IPage<Goods> userIPage = goodsMapper.selectPage(pageParam, queryWrapper);
        long total = pageParam.getTotal();
        long pages = pageParam.getPages();
        List<Goods> goods = userIPage.getRecords();
        return R.ok().message("查询成功").data("data",goods).data("total",total).data("pages",pages);
    }

    @Override
    public R findGoodsBySelect(String name, Long page, Long limit) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("gname",name);
        Page<Goods> pageParam = new Page<>(page, limit);
        IPage<Goods> userIPage = goodsMapper.selectPage(pageParam, queryWrapper);
        long total = pageParam.getTotal();
        long pages = pageParam.getPages();
        List<Goods> goods = userIPage.getRecords();
        return R.ok().message("查询成功").data("data",goods).data("total",total).data("pages",pages);
    }

    @Override
    public R findGoodById(Integer gid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("gid",gid);
        Goods goods=goodsMapper.selectOne(queryWrapper);
        System.out.println(goods);
        return R.ok().message("查询成功").data("data",goods);
    }

    @Override
    public R addGood(Goods goods) {
        int result = goodsMapper.insert(goods);
        if (result==1){
            return R.ok();
        }
        return R.error();
    }

}

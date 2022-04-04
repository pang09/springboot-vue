package com.example.springbootvue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootvue.entity.Goods;
import com.example.springbootvue.entity.R;

public interface GoodsService extends IService<Goods> {

    R selectPage(Long page,Long limit);

    R selectPageByCid(Integer cid,Long page,Long limit);

    R findGoodsBySelect(String name, Long page, Long limit);

    R findGoodById(Integer gid);

    R addGood(Goods goods);

    R updateGood(Goods goods);

    R deleteGood(Integer gid);
}

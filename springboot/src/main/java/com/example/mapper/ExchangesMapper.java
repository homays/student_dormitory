package com.example.mapper;

import com.example.entity.Exchanges;

import java.util.List;

public interface ExchangesMapper {

    /**
      * 新增
    */
    int insert(Exchanges exchanges);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 根据ID查询
    */
    Exchanges selectById(Integer id);

    /**
      * 查询所有
    */
    List<Exchanges> selectAll(Exchanges exchanges);

}
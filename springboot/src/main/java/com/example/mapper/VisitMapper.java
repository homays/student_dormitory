package com.example.mapper;

import com.example.entity.Visit;

import java.util.List;

public interface VisitMapper {

    /**
      * 新增
    */
    int insert(Visit visit);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Visit visit);

    /**
      * 根据ID查询
    */
    Visit selectById(Integer id);

    /**
      * 查询所有
    */
    List<Visit> selectAll(Visit visit);

}
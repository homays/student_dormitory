package com.example.mapper;

import com.example.entity.Building;

import java.util.List;

public interface BuildingMapper {

    /**
      * 新增
    */
    int insert(Building building);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Building building);

    /**
      * 根据ID查询
    */
    Building selectById(Integer id);

    /**
      * 查询所有
    */
    List<Building> selectAll(Building building);

}
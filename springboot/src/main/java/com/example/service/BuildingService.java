package com.example.service;

import com.example.entity.Building;
import com.example.mapper.BuildingMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuildingService {

    @Resource
    private BuildingMapper buildingMapper;

    /**
     * 新增
     */
    public void add(Building building) {
        buildingMapper.insert(building);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        buildingMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            buildingMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Building building) {
        buildingMapper.updateById(building);
    }

    /**
     * 根据ID查询
     */
    public Building selectById(Integer id) {
        return buildingMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Building> selectAll(Building building) {
        return buildingMapper.selectAll(building);
    }

    /**
     * 分页查询
     */
    public PageInfo<Building> selectPage(Building building, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Building> list = buildingMapper.selectAll(building);
        return PageInfo.of(list);
    }

}
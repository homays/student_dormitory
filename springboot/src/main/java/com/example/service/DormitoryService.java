package com.example.service;

import com.example.entity.Building;
import com.example.entity.Dormitory;
import com.example.mapper.BuildingMapper;
import com.example.mapper.DormitoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DormitoryService {

    @Resource
    private DormitoryMapper dormitoryMapper;
    @Resource
    private BuildingMapper buildingMapper;

    /**
     * 新增
     */
    public void add(Dormitory dormitory) {
        dormitoryMapper.insert(dormitory);
        // 宿舍楼信息那边的宿舍数要随之同步
        Building building = buildingMapper.selectById(dormitory.getBuildingId());
        building.setNum(building.getNum() + 1);
        buildingMapper.updateById(building);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        Dormitory dormitory = dormitoryMapper.selectById(id);
        dormitoryMapper.deleteById(id);
        // 宿舍楼信息那边的宿舍数也要随之同步
        Building building = buildingMapper.selectById(dormitory.getBuildingId());
        building.setNum(building.getNum() - 1);
        buildingMapper.updateById(building);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            dormitoryMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Dormitory dormitory) {
        dormitoryMapper.updateById(dormitory);
    }

    /**
     * 根据ID查询
     */
    public Dormitory selectById(Integer id) {
        return dormitoryMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Dormitory> selectAll(Dormitory dormitory) {
        return dormitoryMapper.selectAll(dormitory);
    }

    /**
     * 分页查询
     */
    public PageInfo<Dormitory> selectPage(Dormitory dormitory, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dormitory> list = dormitoryMapper.selectAll(dormitory);
        return PageInfo.of(list);
    }

}
package com.example.service;

import com.example.entity.Visit;
import com.example.mapper.VisitMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VisitService {

    @Resource
    private VisitMapper visitMapper;

    /**
     * 新增
     */
    public void add(Visit visit) {
        visitMapper.insert(visit);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        visitMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            visitMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Visit visit) {
        visitMapper.updateById(visit);
    }

    /**
     * 根据ID查询
     */
    public Visit selectById(Integer id) {
        return visitMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Visit> selectAll(Visit visit) {
        return visitMapper.selectAll(visit);
    }

    /**
     * 分页查询
     */
    public PageInfo<Visit> selectPage(Visit visit, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Visit> list = visitMapper.selectAll(visit);
        return PageInfo.of(list);
    }

}
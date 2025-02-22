package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Electro;
import com.example.exception.CustomException;
import com.example.mapper.ElectroMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ElectroService {

    @Resource
    private ElectroMapper electroMapper;

    /**
     * 新增
     */
    public void add(Electro electro) {
        Electro dbElectro = electroMapper.selectByDormitoryIdAndContent(electro.getDormitoryId(), electro.getContent());
        if (ObjectUtil.isNotEmpty(dbElectro)) {
            throw new CustomException("-1", "您已经有相关记录了，请勿重复提交");
        }
        electroMapper.insert(electro);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        electroMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            electroMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Electro electro) {
        electroMapper.updateById(electro);
    }

    /**
     * 根据ID查询
     */
    public Electro selectById(Integer id) {
        return electroMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Electro> selectAll(Electro electro) {
        return electroMapper.selectAll(electro);
    }

    /**
     * 分页查询
     */
    public PageInfo<Electro> selectPage(Electro electro, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Electro> list = electroMapper.selectAll(electro);
        return PageInfo.of(list);
    }

}
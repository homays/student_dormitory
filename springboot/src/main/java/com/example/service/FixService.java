package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Fix;
import com.example.mapper.FixMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FixService {

    @Resource
    private FixMapper fixMapper;

    /**
     * 新增
     */
    public void add(Fix fix) {
        fix.setTime(DateUtil.now());
        fixMapper.insert(fix);
    }

    public PageInfo<Fix> selectPage(Fix fix, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.STUDENT.name().equals(currentUser.getRole())) {
            fix.setStudentId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Fix> list = fixMapper.selectAll(fix);
        return PageInfo.of(list);
    }

    public void updateById(Fix fix) {
        fixMapper.updateById(fix);
    }

    public void deleteById(Integer id) {
        fixMapper.deleteById(id);
    }
}
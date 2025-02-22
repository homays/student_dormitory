package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Fix;
import com.example.entity.Stay;
import com.example.exception.CustomException;
import com.example.mapper.FixMapper;
import com.example.mapper.StayMapper;
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
    @Resource
    private StayMapper stayMapper;

    /**
     * 新增
     */
    public void add(Fix fix) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (currentUser.getRole().equals(RoleEnum.STUDENT.name())) {
            Stay stay = stayMapper.selectByStudentId(currentUser.getId());
            if (ObjectUtil.isNull(stay)) {
                throw new CustomException("-1", "请先绑定宿舍");
            }
            if (ObjectUtil.notEqual(stay.getDormitoryId(), fix.getDormitoryId())) {
                throw new CustomException("-1", "请选择报修自己的寝室");
            }
        }
        fix.setTime(DateUtil.now());
        fixMapper.insert(fix);
    }

    public PageInfo<Fix> selectPage(Fix fix, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.STUDENT.name().equals(currentUser.getRole())) {
            // 学生只能看到自己寝室的费用缴纳信息
            Stay res = stayMapper.selectByStudentId(currentUser.getId());
            fix.setDormitoryId(res.getDormitoryId());
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
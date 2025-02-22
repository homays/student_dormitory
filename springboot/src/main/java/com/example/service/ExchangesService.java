package com.example.service;

import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Exchanges;
import com.example.mapper.ExchangesMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExchangesService {

    @Resource
    private ExchangesMapper exchangesMapper;

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        exchangesMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            exchangesMapper.deleteById(id);
        }
    }

    /**
     * 分页查询
     */
    public PageInfo<Exchanges> selectPage(Exchanges exchanges, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Account currentUser = TokenUtils.getCurrentUser();
        if (currentUser.getRole().equals(RoleEnum.STUDENT.name())) {
            exchanges.setStudenta(currentUser.getName());
        }
        List<Exchanges> list = exchangesMapper.selectAll(exchanges);
        return PageInfo.of(list);
    }
}
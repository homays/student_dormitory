package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Dormitory;
import com.example.entity.Exchanges;
import com.example.entity.Stay;
import com.example.exception.CustomException;
import com.example.mapper.DormitoryMapper;
import com.example.mapper.ExchangesMapper;
import com.example.mapper.StayMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StayService {

    @Resource
    private StayMapper stayMapper;
    @Resource
    private DormitoryMapper dormitoryMapper;
    @Resource
    private ExchangesMapper exchangesMapper;

    /**
     * 新增
     */
    public void add(Stay stay) {
        // 查询出当前学生有没有住宿信息
        Stay dbStay = stayMapper.selectByStudentId(stay.getStudentId());
        if (ObjectUtil.isNotEmpty(dbStay)) {
            throw new CustomException("-1", "该学生已经有住宿信息了，请勿重复添加");
        }
        // 当前宿舍的床位有没有人住
        Stay dbStay2 = stayMapper.selectByDormitoryIdAndBed(stay.getDormitoryId(), stay.getBed());
        if (ObjectUtil.isNotEmpty(dbStay2)) {
            throw new CustomException("-1", "当前宿舍该床位已经有住宿了，请重新分配");
        }
        if (ObjectUtil.isNotEmpty(stay.getDormitoryId())) {
            // 根据寝室ID查询出寝室信息
            Dormitory dormitory = dormitoryMapper.selectById(stay.getDormitoryId());
            if (ObjectUtil.isNotEmpty(dormitory)) {
                // 先去校验一下该宿舍有没有住满
                if (dormitory.getNum().equals(dormitory.getNowNum())) {
                    throw new CustomException("-1", "该宿舍已住满，请分配到其他宿舍");
                }
                // 初始化宿舍楼ID字段
                stay.setBuildingId(dormitory.getBuildingId());
                // 同步宿舍表里对应的已住人数
                dormitory.setNowNum(dormitory.getNowNum() + 1);
                dormitoryMapper.updateById(dormitory);
            }
        }
        stayMapper.insert(stay);

    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        Stay stay = stayMapper.selectById(id);
        stayMapper.deleteById(id);
        // 更新对应宿舍的住宿人数
        Dormitory dormitory = dormitoryMapper.selectById(stay.getDormitoryId());
        if (ObjectUtil.isNotEmpty(dormitory)) {
            dormitory.setNowNum(dormitory.getNowNum() - 1);
            dormitoryMapper.updateById(dormitory);
        }
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            stayMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Stay stay) {
        stayMapper.updateById(stay);
    }

    /**
     * 根据ID查询
     */
    public Stay selectById(Integer id) {
        return stayMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Stay> selectAll(Stay stay) {
        return stayMapper.selectAll(stay);
    }

    /**
     * 分页查询
     */
    public PageInfo<Stay> selectPage(Stay stay, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Stay> list = stayMapper.selectAll(stay);
        return PageInfo.of(list);
    }

    public void exchange(Stay stay) {
        if (stay.getStudentId().equals(stay.getExStudentId())) {
            throw new CustomException("-1", "请不要选择同一个学生更换");
        }
        // stay里面保存了学生A的住宿信息，exStay里面保存了学生B的住宿信息
        Integer exStudentId = stay.getExStudentId();
        Stay exStay = stayMapper.selectByStudentId(exStudentId);
        if (ObjectUtil.isNull(exStay)) {
            throw new CustomException("-1", "该学生暂无住宿信息，请重新选择");
        }

        // 如何调换a和b的值？首先创建一个新的变量c，然后把a的值赋值给c，再把b的值赋值给a，最后再把c的值赋值给b
        // 1. 创建一个临时的住宿信息tmpStay，把学生A（stay）数据暂存起来
        Stay tmpStay = new Stay();
        BeanUtils.copyProperties(stay, tmpStay);

        // 2. 把学生B（exStay）的数据赋值到stay里面（id不能赋值）
        stay.setBed(exStay.getBed());
        stay.setDormitoryId(exStay.getDormitoryId());
        stay.setBuildingId(exStay.getBuildingId());

        // 3. 把tmpStay（学生A的）的数据复制到exStay里面（id不能赋值）
        exStay.setBed(tmpStay.getBed());
        exStay.setDormitoryId(tmpStay.getDormitoryId());
        exStay.setBuildingId(tmpStay.getBuildingId());

        // 更新学生A和学生B的住宿信息
        stayMapper.updateById(stay);
        stayMapper.updateById(exStay);

        // 生成一条换寝记录
        Exchanges exchanges = new Exchanges();
        exchanges.setStudenta(stay.getStudentName());
        exchanges.setBeda(exStay.getBed());
        exchanges.setDormitorya(stay.getDormitoryName());
        exchanges.setStudentb(exStay.getStudentName());
        exchanges.setBedb(stay.getBed());
        exchanges.setDormitoryb(exStay.getDormitoryName());
        exchanges.setTime(DateUtil.now());
        exchangesMapper.insert(exchanges);
    }
}
package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Stay;
import com.example.exception.CustomException;
import com.example.service.StayService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/stay")
public class StayController {

    @Resource
    private StayService stayService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Stay stay) {
        if (ObjectUtil.isEmpty(stay.getStudentId())
                || ObjectUtil.isEmpty(stay.getDormitoryId())
                || ObjectUtil.isEmpty(stay.getBed())) {
            throw new CustomException("-1", "请完善信息");
        }
        stayService.add(stay);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        stayService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        stayService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Stay stay) {
        stayService.updateById(stay);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Stay stay = stayService.selectById(id);
        return Result.success(stay);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Stay stay ) {
        List<Stay> list = stayService.selectAll(stay);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Stay stay,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Stay> page = stayService.selectPage(stay, pageNum, pageSize);
        return Result.success(page);
    }

}
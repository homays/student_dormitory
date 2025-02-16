package com.example.controller;

import com.example.common.Result;
import com.example.entity.Dormitory;
import com.example.service.DormitoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dormitory")
public class DormitoryController {

    @Resource
    private DormitoryService dormitoryService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Dormitory dormitory) {
        dormitoryService.add(dormitory);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        dormitoryService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        dormitoryService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Dormitory dormitory) {
        dormitoryService.updateById(dormitory);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Dormitory dormitory = dormitoryService.selectById(id);
        return Result.success(dormitory);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Dormitory dormitory ) {
        List<Dormitory> list = dormitoryService.selectAll(dormitory);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Dormitory dormitory,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Dormitory> page = dormitoryService.selectPage(dormitory, pageNum, pageSize);
        return Result.success(page);
    }

}
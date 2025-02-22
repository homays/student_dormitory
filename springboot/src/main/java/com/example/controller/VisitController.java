package com.example.controller;

import com.example.common.Result;
import com.example.entity.Visit;
import com.example.service.VisitService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/visit")
public class VisitController {

    @Resource
    private VisitService visitService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Visit visit) {
        visitService.add(visit);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        visitService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        visitService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Visit visit) {
        visitService.updateById(visit);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Visit visit = visitService.selectById(id);
        return Result.success(visit);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Visit visit ) {
        List<Visit> list = visitService.selectAll(visit);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Visit visit,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Visit> page = visitService.selectPage(visit, pageNum, pageSize);
        return Result.success(page);
    }

}
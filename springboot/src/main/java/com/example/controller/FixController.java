package com.example.controller;

import com.example.common.Result;
import com.example.entity.Fix;
import com.example.service.FixService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/fix")
public class FixController {

    @Resource
    private FixService fixService;

    @PostMapping("/add")
    public Result add(@RequestBody Fix fix) {
        fixService.add(fix);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Fix fix) {
        fixService.updateById(fix);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        fixService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/selectPage")
    public Result selectPage(Fix fix,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Fix> page = fixService.selectPage(fix, pageNum, pageSize);
        return Result.success(page);
    }
}
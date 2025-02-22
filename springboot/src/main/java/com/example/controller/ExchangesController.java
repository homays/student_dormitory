package com.example.controller;

import com.example.common.Result;
import com.example.entity.Exchanges;
import com.example.service.ExchangesService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/exchanges")
public class ExchangesController {

    @Resource
    private ExchangesService exchangesService;

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        exchangesService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        exchangesService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Exchanges exchanges,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Exchanges> page = exchangesService.selectPage(exchanges, pageNum, pageSize);
        return Result.success(page);
    }
}
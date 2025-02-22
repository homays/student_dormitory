package com.example.controller;

import com.example.common.Result;
import com.example.entity.Electro;
import com.example.service.ElectroService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/electro")
public class ElectroController {

    @Resource
    private ElectroService electroService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Electro electro) {
        electroService.add(electro);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        electroService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        electroService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Electro electro) {
        electroService.updateById(electro);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Electro electro = electroService.selectById(id);
        return Result.success(electro);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Electro electro ) {
        List<Electro> list = electroService.selectAll(electro);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Electro electro,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Electro> page = electroService.selectPage(electro, pageNum, pageSize);
        return Result.success(page);
    }

}
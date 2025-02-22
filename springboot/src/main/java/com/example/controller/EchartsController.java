package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Dormitory;
import com.example.entity.Electro;
import com.example.service.DormitoryService;
import com.example.service.ElectroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Resource
    private ElectroService electroService;
    @Resource
    private DormitoryService dormitoryService;

    @GetMapping("/electro")
    public Result electro() {
        List<Map<String, Object>> list = new ArrayList<>();
        // 封装这个list
        List<Electro> electros = electroService.selectAll(new Electro());
        Map<String, Long> collect = electros.stream()
                .filter(x -> ObjectUtil.isNotEmpty(x.getStatus()))
                .collect(Collectors.groupingBy(Electro::getStatus, Collectors.counting()));
        for (String key : collect.keySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", key);
            map.put("value", collect.get(key));
            list.add(map);
        }
        return Result.success(list);
    }

    @GetMapping("/stay")
    public Result stay() {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Dormitory> dormitories = dormitoryService.selectAll(new Dormitory());
        long count = dormitories.stream().filter(x -> x.getNum().equals(x.getNowNum())).count();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "已住满");
        map1.put("value", count);
        list.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "未住满");
        map2.put("value", dormitories.size() - count);
        list.add(map2);

        return Result.success(list);
    }
}
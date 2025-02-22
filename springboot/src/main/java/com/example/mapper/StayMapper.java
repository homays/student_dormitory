package com.example.mapper;

import com.example.entity.Stay;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StayMapper {

    /**
      * 新增
    */
    int insert(Stay stay);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Stay stay);

    /**
      * 根据ID查询
    */
    Stay selectById(Integer id);

    /**
      * 查询所有
    */
    List<Stay> selectAll(Stay stay);

    @Select("select stay.*, student.name as studentName, dormitory.code as dormitoryName from stay " +
            "left join student on stay.student_id = student.id " +
            "left join dormitory on stay.dormitory_id = dormitory.id " +
            "where student_id = #{studentId}")
    Stay selectByStudentId(Integer studentId);

    @Select("select * from stay where dormitory_id = #{dormitoryId} and bed = #{bed}")
    Stay selectByDormitoryIdAndBed(@Param("dormitoryId") Integer dormitoryId, @Param("bed") String bed);
}
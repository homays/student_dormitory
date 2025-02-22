package com.example.entity;

import java.io.Serializable;

public class Stay implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer studentId;
    private Integer dormitoryId;
    private Integer buildingId;
    private String bed;

    private String studentName;
    private String dormitoryName;
    private String buildingName;
    private Integer exStudentId;

    public Integer getExStudentId() {
        return exStudentId;
    }

    public void setExStudentId(Integer exStudentId) {
        this.exStudentId = exStudentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
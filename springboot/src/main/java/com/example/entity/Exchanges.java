package com.example.entity;

import java.io.Serializable;

public class Exchanges implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String studenta;
    private String studentb;
    private String dormitorya;
    private String dormitoryb;
    private String beda;
    private String bedb;
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudenta() {
        return studenta;
    }

    public void setStudenta(String studenta) {
        this.studenta = studenta;
    }

    public String getStudentb() {
        return studentb;
    }

    public void setStudentb(String studentb) {
        this.studentb = studentb;
    }

    public String getDormitorya() {
        return dormitorya;
    }

    public void setDormitorya(String dormitorya) {
        this.dormitorya = dormitorya;
    }

    public String getDormitoryb() {
        return dormitoryb;
    }

    public void setDormitoryb(String dormitoryb) {
        this.dormitoryb = dormitoryb;
    }

    public String getBeda() {
        return beda;
    }

    public void setBeda(String beda) {
        this.beda = beda;
    }

    public String getBedb() {
        return bedb;
    }

    public void setBedb(String bedb) {
        this.bedb = bedb;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
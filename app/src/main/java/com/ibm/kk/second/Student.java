package com.ibm.kk.second;

import java.io.Serializable;

public class Student implements Serializable {
    private long id;
    private String name;
    private String num;
    private String grade;
    private String period;
    private String type;
    private String place;
    private String trainDate;
    private String modifyDateTime;

    public Student() {
        super();
    }

    public Student(long id, String name, String num, String period, String grade, String type, String place,
                   String trainDate, String modifyDateTime) {
        super();
        this.id = id;
        this.name = name;
        this.num = num;
        this.period = period;
        this.grade = grade;
        this.type = type;
        this.place = place;
        this.trainDate = trainDate;
        this.modifyDateTime = modifyDateTime;
    }
    public Student(String name, String num, String period, String grade, String type, String place,
                   String trainDate, String modifyDateTime) {
        super();
        this.name = name;
        this.num = num;
        this.period = period;
        this.grade = grade;
        this.type = type;
        this.place = place;
        this.trainDate = trainDate;
        this.modifyDateTime = modifyDateTime;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getGrade() {
        return grade;     }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getPeriod() {
        return period;     }
    public void setPeriod(String period) {
        this.period = period;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;     }
    public String getTrainDate() {
        return trainDate;
    }
    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }
    public String getModifyDateTime() {
        return modifyDateTime;
    }
    public void setModifyDateTime(String modifyDateTime) {
        this.modifyDateTime = modifyDateTime;
    }
}



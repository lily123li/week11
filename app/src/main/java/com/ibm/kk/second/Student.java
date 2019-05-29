package com.ibm.kk.second;

import java.io.Serializable;

public class Student implements Serializable {
    private long id;
    private String name;
    private int num;
    private String sex;
    private String major;
    private String like;
    private String phoneNumber;
    private String trainDate;
    private String modifyDateTime;

    public Student() {
        super();
    }

    public Student(long id, String name, int num, String major,String sex, String like, String phoneNumber,
                   String trainDate, String modifyDateTime) {
        super();
        this.id = id;
        this.name = name;
        this.num = num;
        this.major = major;
        this.sex = sex;
        this.like = like;
        this.phoneNumber = phoneNumber;
        this.trainDate = trainDate;
        this.modifyDateTime = modifyDateTime;
    }
    public Student(String name, int num, String major, String sex, String like, String phoneNumber,
                   String trainDate, String modifyDateTime) {
        super();
        this.name = name;
        this.num = num;
        this.major = major;
        this.sex = sex;
        this.like = like;
        this.phoneNumber = phoneNumber;
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
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public String getSex() {
        return sex;     }
    public void setMajor(String major) {
        this.major = major;
    }
    public String getMajor() {
        return major;     }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getLike() {
        return like;
    }
    public void setLike(String like) {
        this.like = like;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;     }
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



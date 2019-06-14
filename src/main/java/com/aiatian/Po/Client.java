package com.aiatian.Po;

import java.util.Date;

public class Client {

    public  Integer id;

    //用户的名字
    public  String fromUserName;

    // 签到的天数
    public Integer numberOfDays;

    //当前签到天
    public Date toDay;

    // 积分
    public Integer integral;

    //已用积分
    public Integer haveUsedIntegral;

    //现有积分
    public Integer nowTheIntegral;



    public Client(String fromUserName, Integer numberOfDays, Date toDay, Integer integral) {
        this.fromUserName = fromUserName;
        this.numberOfDays = numberOfDays;
        this.toDay = toDay;
        this.integral = integral;
    }

    public Client(Integer id, String fromUserName, Integer numberOfDays, Date toDay, Integer integral) {
        this.id = id;
        this.fromUserName = fromUserName;
        this.numberOfDays = numberOfDays;
        this.toDay = toDay;
        this.integral = integral;
    }

    public Client(String fromUserName, Integer numberOfDays, Date toDay, Integer integral, Integer haveUsedIntegral, Integer nowTheIntegral) {
        this.fromUserName = fromUserName;
        this.numberOfDays = numberOfDays;
        this.toDay = toDay;
        this.integral = integral;
        this.haveUsedIntegral = haveUsedIntegral;
        this.nowTheIntegral = nowTheIntegral;
    }

    public Integer getHaveUsedIntegral() {
        return haveUsedIntegral;
    }

    public void setHaveUsedIntegral(Integer haveUsedIntegral) {
        this.haveUsedIntegral = haveUsedIntegral;
    }

    public Integer getNowTheIntegral() {
        return nowTheIntegral;
    }

    public void setNowTheIntegral(Integer nowTheIntegral) {
        this.nowTheIntegral = nowTheIntegral;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Date getToDay() {
        return toDay;
    }

    public void setToDay(Date toDay) {
        this.toDay = toDay;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fromUserName='" + fromUserName + '\'' +
                ", numberOfDays=" + numberOfDays +
                ", toDay=" + toDay +
                ", integral=" + integral +
                '}';
    }

    public Client() {
        super();
    }
}

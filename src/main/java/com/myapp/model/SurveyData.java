package com.myapp.model;

// 此类是为了方便接收从前端向后端发送的JSON格式的问卷数据
public class SurveyData {
    private String username;
    private String gender;
    private Integer healthScore;
    private Integer mentalScore;
    private Integer riskScore;

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public Integer getHealthScore() {
        return healthScore;
    }

    public Integer getMentalScore() {
        return mentalScore;
    }

    public Integer getRiskScore() {
        return riskScore;
    }
}

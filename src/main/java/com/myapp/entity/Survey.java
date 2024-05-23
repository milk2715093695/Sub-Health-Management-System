package com.myapp.entity;

import com.myapp.model.SurveyData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// 此类代表问卷结果，使用Jakarta Persistence注解进行数据库映射
@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;
    private String gender;
    private Integer healthScore;
    private Integer mentalScore;
    private Integer riskScore;

    // 使用surveyData作为参数的构造函数
    public Survey(SurveyData surveyData) {
        this.username = surveyData.getUsername();
        this.gender = surveyData.getGender();
        this.healthScore = surveyData.getHealthScore();
        this.mentalScore = surveyData.getMentalScore();
        this.riskScore = surveyData.getRiskScore();
    }

    public Survey() {}

    public Long getId() {
        return id;
    }

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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHealthScore(Integer healthScore) {
        this.healthScore = healthScore;
    }

    public void setMentalScore(Integer mentalScore) {
        this.mentalScore = mentalScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }
}

package com.myapp.entity;

import com.myapp.model.SurveyData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private Integer healthScore;
    private Integer mentalScore;
    private Integer riskScore;

    public Survey(SurveyData surveyData) {
        this.username = surveyData.getUsername();
        this.healthScore = surveyData.getHealthScore();
        this.mentalScore = surveyData.getMentalScore();
        this.riskScore = surveyData.getRiskScore();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

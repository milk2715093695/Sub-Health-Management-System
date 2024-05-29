package com.myapp.model;

// 此类是为了方便接收从前端向后端发送的JSON格式的问卷数据
public record SurveyData(String username, String gender, Integer healthScore, Integer mentalScore, Integer riskScore) {}
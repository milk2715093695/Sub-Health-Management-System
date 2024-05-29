package com.myapp.model;

// 此类是为了方便接受从前端向后端发送的JSON格式的用户数据
public record UserData(String username, String password) {}
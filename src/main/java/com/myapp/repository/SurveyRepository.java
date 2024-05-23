package com.myapp.repository;

import com.myapp.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

// SurveyRepository接口
// 此接口继承了JpaRepository，使用Spring Data JPA进行数据库操作
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    // 这个类会被接口自动实现，功能是根据用户名查找对应的Survey记录
    Survey findByUsername(String username);
}
package com.myapp.Repository;

import com.myapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// UserRepository接口
// 此接口继承了JpaRepository，使用Spring Data JPA进行数据库操作
public interface UserRepository extends JpaRepository<User, Long> {
    // 这个类会被接口自动实现，功能是根据用户名查找对应的用户数据
    User findByUsername(String username);
}
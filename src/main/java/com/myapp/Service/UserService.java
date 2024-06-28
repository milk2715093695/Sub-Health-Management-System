package com.myapp.Service;

import com.myapp.Entity.User;
import com.myapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  // `Service` 注解

@Service
public class UserService {
    private final UserRepository userRepository;

    // 自动注入实例（这样可以不需要手动创建实例）
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 注册用户
     *
     * @param username 用户名
     * @param password 密码
     *
     * @return 如果用户已经存在，返回false，否则保存新用户并返回true
     */
    public boolean register(String username, String password) {
        User existingUser = userRepository.findByUsername(username);

        if (existingUser != null) {
            return false;
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userRepository.save(newUser);

        return true;
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     *
     * @return 如果用户名和密码匹配，返回对应的用户实例， 否则返回null
     */
    public User login(String username, String password) {
        User existingUser = userRepository.findByUsername(username);

        if (existingUser != null && existingUser.getPassword().equals(password)) {
            return existingUser;
        } else {
            return null;
        }
    }
}

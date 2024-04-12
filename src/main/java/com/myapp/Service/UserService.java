package com.myapp.Service;

import com.myapp.entity.User;
import com.myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  // `Service` 注解

@Service
public class UserService {
    @Autowired UserRepository userRepository;

    public boolean register(String username, String password) {
        User existingUser = userRepository.findByUsername(username);

        if (existingUser != null) {
            System.out.println("用户名被占用");
            return false;
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userRepository.save(newUser);

        return true;
    }
}

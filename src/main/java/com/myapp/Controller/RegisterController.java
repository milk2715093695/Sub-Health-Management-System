package com.myapp.Controller;

import com.myapp.entity.User;
import com.myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password) {

        // 使用findByUsername方法来检查用户名是否存在
        User existingUser = userRepository.findByUsername(username);

        // 检查返回的User是否为null
        if(existingUser != null){
            System.out.println("用户名已存在");
            return "redirect:/html/register.html";  // 如果用户名存在，重定向到注册页面
        }

        // 如果用户名不存在，那么创建新的User并保存到数据库
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        userRepository.save(newUser);

        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);

        return "redirect:/html/registerSuccess.html";  // 如果注册成功，重定向到成功页面
    }
}
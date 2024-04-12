package com.myapp.Controller;

import com.myapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @Autowired UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        // 调用注册服务
        boolean success = userService.register(username, password);

        if (!success) {
            System.out.println("用户名已存在");
            return "redirect:/html/register.html";
        }

        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);

        return "redirect:/html/registerSuccess.html";
    }
}
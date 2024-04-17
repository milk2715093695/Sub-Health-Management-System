package com.myapp.Controller;

import com.myapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.HashMap;

@Controller
public class RegisterController {
    @Autowired UserService userService;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam("username") String username,
                                        @RequestParam("password") String password) {

        Map<String, Object> result = new HashMap<>();
        boolean success = userService.register(username, password);     //调用注册服务

        if (!success) {
            result.put("errMessage", "用户名已被占用，请重新输入");
        }

        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);

        return result;
    }
}
package com.myapp.Controller;

import com.myapp.model.UserData;
import com.myapp.entity.User;
import com.myapp.Service.UserService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.HashMap;

@RestController  // 标识为Spring MVC RestController类
public class LoginController {
    @Autowired UserService userService;  // 通过Spring框架自动注入UserService实例

    @PostMapping("/login")  // 处理到/login的POST请求
    public Map<String, Object> login(@RequestBody UserData userData) {
        Map<String, Object> result = new HashMap<>();
        User loginuser = userService.login(userData.getUsername(), userData.getPassword());     //调用登录服务

        // 验证并处理登录结果
        if (loginuser == null) {
            Map<String, String> errMessage = new HashMap<>();
            errMessage.put("zh-CN", "用户名或密码错误，请重新输入");
            errMessage.put("en", "Username or password is incorrect, please try again");
            result.put("errMessage", errMessage);
            result.put("success", false);
        } else {
            result.put("success", true);
        }

        return result;  // 返回处理结果
    }
}
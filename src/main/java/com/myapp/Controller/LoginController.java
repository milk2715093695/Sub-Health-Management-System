package com.myapp.Controller;

import com.myapp.Service.UserService;
import com.myapp.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller  // 标识为Spring MVC Controller类
public class LoginController {
    @Autowired UserService userService;  // 通过Spring框架自动注入UserService实例

    @PostMapping("/login")  // 处理到/login的POST请求
    public Map<String, Object> login(
            @RequestParam String username,  // 请求参数
            @RequestParam String password) {

        Map<String, Object> result = new HashMap<>();
        User loginuser = userService.login(username, password);

        // 验证并处理登录结果
        if (loginuser == null) {
            result.put("errMessage", "用户名或密码错误");
            result.put("success", false);
        } else {
            result.put("success", true);
        }

        return result;  // 返回处理结果
    }
}
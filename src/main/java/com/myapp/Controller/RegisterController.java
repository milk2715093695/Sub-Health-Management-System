package com.myapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        // 此处可以处理提交过来的表单数据，例如将用户信息保存到数据库
        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);

        return "redirect:/html/registerSuccess.html";
    }
}
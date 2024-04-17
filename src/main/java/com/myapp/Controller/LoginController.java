package com.myapp.Controller;

import com.myapp.Service.UserService;
import com.myapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String username,
                     @RequestParam String password) {
        Map<String, Object> result = new HashMap<>();
        User loginuser = userService.login(username, password);

        if (loginuser == null) {
            result.put("errMessage", "用户名或密码错误");
            result.put("success", false);
        } else {
            result.put("success", true);
        }

        return result;
    }
}

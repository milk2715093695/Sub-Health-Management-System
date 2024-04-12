package com.myapp.Controller;

import com.myapp.entity.User;
import com.myapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {
        User loginuser = userService.login(username, password);

        if (loginuser != null) {
            return "redirect:/html/loginSuccess.html";
        } else {
            return "redirect:/html/login.html";
        }
    }

}

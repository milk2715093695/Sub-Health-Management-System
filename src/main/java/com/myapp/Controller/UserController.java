package com.myapp.Controller;


import com.myapp.Service.AddErrorMessage;
import com.myapp.Service.UserService;
import com.myapp.entity.User;
import com.myapp.model.UserData;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理登录注册请求的控制器
 * @author milk
 */
@RestController
public class UserController {
    private final UserService userService;
    private final AddErrorMessage addErrorMessage;

    /**
     * 构造函数，使用@Autowired来自动注入UserService的实例。
     *
     * @param userService 用户服务提供者。
     * @param addErrorMessage 填写错误信息的服务。
     */
    @Autowired
    public UserController(UserService userService, AddErrorMessage addErrorMessage) {
        this.userService = userService;
        this.addErrorMessage = addErrorMessage;
    }

    /**
     * 处理登录请求。
     *
     * @param session 用户会话信息，用于存储登录的用户。
     * @param userData 前端发来的用户信息。
     * @return 包含登录结果信息的ResponseEntity对象。
     */
    @PostMapping("/login")
    public Map<String, Object> login(HttpSession session, @RequestBody UserData userData) {
        Map<String, Object> result = new HashMap<>();
        User loginuser = userService.login(userData.username(), userData.password());     //调用登录服务

        // 验证并处理登录结果
        if (loginuser == null) {
            addErrorMessage.addErrorMessage(result, "用户名或密码错误，请重新输入", "Username or password is incorrect, please try again");
        } else {
            // 登录成功后设置session为用户名
            session.setAttribute("user", loginuser);
            result.put("success", true);
        }

        return result;
    }

    /**
     * 处理注册请求。
     *
     * @param userData 来自请求体的用户数据，包含用户名和密码。
     * @return 包含注册结果信息的Map对象。
     */
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserData userData) {
        Map<String, Object> result = new HashMap<>();
        String username = userData.username();
        String password = userData.password();

        // 注册失败时在返回值中添加错误信息
        if (!userService.register(username, password)) {
            addErrorMessage.addErrorMessage(result, "用户名已被占用，请重新输入", "Username already exists, please try another one.");
        } else {
            result.put("success", true);
        }

        return result;
    }
}

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
    // 通过自动注入依赖，将用户服务注入到登录控制器
    @Autowired
    UserService userService;  // 用户服务对象，用于处理所有用户相关的逻辑

    /**
     * 登录方法
     * <p>处理来自客户端的登录请求，要求是POST方式提交，
     * 并且需要在请求体中提供UserData（用户名和密码）</p>
     *
     * @param userData 用户数据（包括用户名和密码）
     *
     * @return 返回一个包含登录操作是否成功以及相关信息的Map。
     * <ul>
     * <li>当登录成功时，返回的map里将会有一个"success"字段并且值为true。</li>
     * <li>当登录失败时，map里将会有两个字段："success"值为false，以及一个错误信息"errMessage"。</li>
     * </ul>
     */
    @PostMapping("/login")
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

        return result;
    }
}
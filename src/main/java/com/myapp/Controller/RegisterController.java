package com.myapp.Controller;

import com.myapp.model.UserData;
import com.myapp.Service.UserService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.HashMap;

@RestController
public class RegisterController {
    private final UserService userService;
    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册方法
     * <p>处理来自客户端的注册请求，要求是POST方法提交，
     * 并且需要在请求体中提供userData（用户名和密码）</p>
     *
     * @param userData 用户数据（包括用户名和密码）
     *
     * @return 返回一个包含注册操作是否成功以及相关信息的Map。
     * <ul>
     * <li>当注册成功时，返回的map里将会有一个"success"字段并且值为true。</li>
     * <li>当注册失败时，map里将会有两个字段："success"值为false，以及一个错误信息"errMessage"。</li>
     * </ul>
     */
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserData userData) {
        Map<String, Object> result = new HashMap<>();
        String username = userData.username();
        String password = userData.password();

        boolean success = userService.register(username, password);     // 调用注册服务
        result.put("success", success);
        // 注册失败时在返回值中添加错误信息
        if (!success) {
            Map<String, String> errMessage = new HashMap<>();
            errMessage.put("zh-CN", "用户名已被占用，请重新输入");
            errMessage.put("en", "Username already exists, please try another one.");
            result.put("errMessage", errMessage);
        }

        return result;
    }
}
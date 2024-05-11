package com.myapp.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@RequestMapping("/session")  // 指定类级别的请求映射
public class ChatController {
    @GetMapping("/set")
    public String setSessionData(HttpSession session) {
        // 在会话中设置一个属性
        session.setAttribute("Greeting", "Hello, world!");
        return "数据已经保存在了会话中.";
    }

    @GetMapping("/get")
    public String getSessionData(HttpSession session) {
        String greeting = (String) session.getAttribute("Greeting");
        if (greeting != null && !greeting.isEmpty()) {
            return "在会话中找到的数据: " + greeting;
        } else {
            return "会话中不存在这个值";
        }
    }

    @GetMapping("/remove")
    public String removeSessionData(HttpSession session) {
        session.removeAttribute("Greeting");
        return "数据已经从会话中移除.";
    }
}
package com.myapp.Controller;


import com.myapp.Entity.User;
import com.myapp.model.APIResponse;
import com.myapp.Service.APIService.IAPIService;
import com.myapp.util.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 处理聊天请求的控制器。
 * @author milk
 */
@RestController
@RequestMapping("/api")
public class ChatController {
    private final IAPIService apiService;

    /**
     * 使用自动装配的IAPIService构造ChatController。
     *
     * @param apiService 用于访问聊天功能的API服务。
     */
    @Autowired
    public ChatController(IAPIService apiService) {
        this.apiService = apiService;
    }

    /**
     * 通过解码用户输入、在会话中检索或设置用户名和聊天历史记录，以及调用API服务处理聊天，处理聊天请求。
     *
     * @param session      HttpSession，用于存储和检索用户会话数据。
     * @param encodedInput 用户输入的URL格式编码字符串。
     * @return SseEmitter 异步响应的SseEmitter对象。
     */
    @GetMapping("/chat")
    public SseEmitter chat(HttpSession session, @RequestParam String encodedInput) {
        User user = (User) session.getAttribute("user");

        String username = (checkUser(user)) ? user.getUsername() : "Guest";
        String userInput = URLDecoder.decode(encodedInput, StandardCharsets.UTF_8);
        JSONArray chatHistory = getHistory(session);

        APIResponse response = apiService.accessAPI(userInput, chatHistory, username, "1");
        SseEmitter emitter = response.sseEmitter();
        JSONObject message = response.message();

        setHistory(session, chatHistory, message, userInput);

        return emitter;
    }

    /**
     * 检查用户是否有效。
     * @param user User对象。
     * @return Boolean 如果用户对象不为null并且用户名有效，则返回true；否则返回false。
     */
    private Boolean checkUser(User user) {
        return user != null && user.getUsername() != null && !user.getUsername().isEmpty();
    }

    /**
     * 检查并返回聊天历史。如果会话中不存在聊天历史，则初始化一个新的JSONArray并保存至会话。
     *
     * @param session HttpSession，用于存储和检索用户会话数据。
     * @return JSONArray 聊天历史的JSONArray对象。
     */
    private JSONArray getHistory(HttpSession session) {
        JSONArray chatHistory = (JSONArray) session.getAttribute("chatHistory");
        if (chatHistory == null) {
            chatHistory = new JSONArray();
            session.setAttribute("chatHistory", chatHistory);
        }
        return chatHistory;
    }

    /**
     * 更新会话中的聊天历史记录。
     * 如果有新消息，它会将用户输入和消息添加到聊天历史，并更新会话中的记录。
     *
     * @param session      HttpSession，用于存储和检索用户会话数据。
     * @param chatHistory  当前的聊天历史JSONArray。
     * @param message      新的消息JSONObject，如果没有新消息则为null。
     * @param userInput    用户的输入字符串。
     */
    private void setHistory(HttpSession session, JSONArray chatHistory, JSONObject message, String userInput) {
        if (message != null) {
            chatHistory.put(JsonParser.createJsonObject("user", userInput, "text"));
            chatHistory.put(message);
            session.setAttribute("chatHistory", chatHistory);
        }
    }
}
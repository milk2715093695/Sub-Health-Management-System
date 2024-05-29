package com.myapp.Controller;

import com.myapp.Service.APIService;
import com.myapp.model.APIResponse;
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

@RestController
@RequestMapping("/api")
public class ChatController {
    private final APIService apiService;

    @Autowired
    public ChatController(APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/chat")
    public SseEmitter chat(HttpSession session, @RequestParam String encodedInput) {
        String userInput = URLDecoder.decode(encodedInput, StandardCharsets.UTF_8);
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = "Guest";
        }

        JSONArray chatHistory = (JSONArray) session.getAttribute("chatHistory");
        if (chatHistory == null) {
            chatHistory = new JSONArray();
            session.setAttribute("chatHistory", chatHistory);
        }

        APIResponse response = apiService.accessAPI(userInput, chatHistory, username, "1");
        SseEmitter emitter = response.getSseEmitter();
        JSONObject message = response.getMessage();

        if (message != null) {
            chatHistory.put(JsonParser.creatJsonObject("user", userInput, "text"));
            chatHistory.put(message);

            session.setAttribute("chatHistory", chatHistory);
        }

        return emitter;
    }
}
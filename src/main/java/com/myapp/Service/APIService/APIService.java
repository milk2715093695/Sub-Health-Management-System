package com.myapp.Service.APIService;


import com.myapp.Model.JsonParserData;
import com.myapp.Model.APIResponse;
import com.myapp.util.HttpClient;
import com.myapp.util.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.net.URL;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 提供API服务的实现类，用于访问聊天功能。
 * 该类在生产环境中运行，并使用HttpClient发送HTTP请求。
 * @author milk
 */
@Profile("prod")
@Service
public class APIService implements IAPIService {
    private final HttpClient httpClient;

    /**
     * 构造方法，初始化HttpClient实例。
     */
    APIService() {
        this.httpClient = new HttpClient();
    }

    /**
     * 访问API并处理用户输入，返回一个包含SseEmitter和消息的APIResponse对象。
     *
     * @param userInput      用户输入的字符串。
     * @param chatHistory    当前会话的聊天历史JSONArray。
     * @param username       当前用户的用户名。
     * @param conversation_id 当前会话的ID。
     * @return APIResponse 包含SseEmitter和消息的API响应对象。
     */
    public APIResponse accessAPI(String userInput, JSONArray chatHistory, String username, String conversation_id) {
        SseEmitter emitter = new SseEmitter(300000L);
        StringBuilder answer = new StringBuilder();
        new Thread(() -> {
            try {
                final String token = System.getenv("TOKEN");
                final String bot_id = System.getenv("BOT_ID");
                URL url = new URL("https://api.coze.com/open_api/v2/chat");

                JSONObject requestBody = JsonParser.createJsonObject(conversation_id, bot_id, username, userInput, true, chatHistory);

                try (BufferedReader reader = httpClient.sendPostRequest(url, requestBody, token)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        JsonParserData parsedData = JsonParser.parseJson(line);
                        if (parsedData != null && !parsedData.isParseComplete()) {
                            String parsedContent = parsedData.parsedContent();
                            if (parsedContent == null) continue;
                            emitter.send("\u200B" + parsedContent);
                            answer.append(parsedContent);
                        } else if (parsedData != null) {
                            emitter.send(SseEmitter.event().name("DONE").data(""));
                            emitter.complete();
                        }
                    }
                }
            } catch (Exception exception) {
                emitter.completeWithError(exception);
            }
        }).start();

        return new APIResponse(emitter, JsonParser.createJsonObject("assistant", "answer", answer.toString(), "text"));
    }
}
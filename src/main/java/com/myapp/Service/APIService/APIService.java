package com.myapp.Service.APIService;

import com.myapp.Model.JsonParserData;
import com.myapp.util.JsonParser;
import com.myapp.Model.APIResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Profile("prod")
@Service
public class APIService implements IAPIService {
    public APIResponse accessAPI(String userInput, JSONArray chatHistory, String username, String conversation_id) {
        SseEmitter emitter = new SseEmitter(300000L);
        StringBuilder answer = new StringBuilder();
        new Thread(() -> {
            try {
                final String token = System.getenv("TOKEN");
                final String bot_id = System.getenv("BOT_ID");

                URL url = new URL("https://api.coze.com/open_api/v2/chat");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // 设置请求方法为POST
                connection.setRequestMethod("POST");

                // 设置请求头部
                connection.setRequestProperty("Authorization", "Bearer " + token);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "*/*");
                connection.setRequestProperty("Host", "api.coze.com");
                connection.setRequestProperty("Connection", "keep-alive");

                // 设置请求体
                JSONObject requestBody = JsonParser.createJsonObject(conversation_id, bot_id, username, userInput, true, chatHistory);

                // 向服务器发送请求
                connection.setDoOutput(true);
                try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                    outputStream.write(requestBody.toString().getBytes());
                }

                // 读取服务器的响应
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
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

                connection.disconnect();
            } catch (Exception exception) {
                emitter.completeWithError(exception);
            }
        }).start();

        return new APIResponse(emitter, JsonParser.createJsonObject("assistant", "answer", answer.toString(), "text"));
    }
}
package com.myapp.Service;

import com.myapp.util.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class APIService {
    public JSONObject accessAPI(String userInput, JSONArray chatHistory, String username, String conversation_id) {
        StringBuilder answer = new StringBuilder();
        //String token = System.getenv("MY_API_TOKEN");
        String token = "pat_7XBJJBTZ5RSwd98QVw4VPJ68jG1hZYHIIuj6xEUxPX2t6XNlfygc4JLNhER4myCk";
        try {
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
            JSONObject requestBody = JsonParser.creatJsonObject(conversation_id, "7366455788757778437", username, userInput, true, chatHistory);

            // 向服务器发送请求
            connection.setDoOutput(true);
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.write(requestBody.toString().getBytes());
            }

            // 读取服务器的响应
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                System.out.print("回复：");
                while ((line = reader.readLine()) != null) {
                    String parsedContent = JsonParser.parseJson(line);
                    if (parsedContent == null) break;
                    System.out.print(parsedContent);
                    answer.append(parsedContent);
                }
            }

            connection.disconnect();
        } catch (Exception exception) {
            return null;
        }

        return JsonParser.creatJsonObject("assistant", "answer", answer.toString(), "text");
    }
}
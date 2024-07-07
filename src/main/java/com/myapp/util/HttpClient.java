package com.myapp.util;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 用于发起http请求的工具类
 * @author milk
 */
public class HttpClient {
    /**
     * 用于向制定的URL发起请求
     *
     * @param url URL
     * @param requestBody 请求体的内容
     * @param token 发起请求时使用的token
     * @return BufferedReader用于读取反馈
     * @throws IOException 当发生IO错误时
     */
    public BufferedReader sendPostRequest(URL url, JSONObject requestBody, String token) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Host", "api.coze.com");
        connection.setRequestProperty("Connection", "keep-alive");

        connection.setDoOutput(true);

        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.write(requestBody.toString().getBytes());
        }

        return new BufferedReader(new InputStreamReader(connection.getInputStream())) {
            @Override
            public void close() throws IOException {
                super.close();
                connection.disconnect();
            }
        };
    }
}

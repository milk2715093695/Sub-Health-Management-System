package com.myapp.util;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
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

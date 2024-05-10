package com.myapp.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
    /**
     * parseJson方法
     *
     * <p>用于解析服务器的相应</p>
     *
     * @param jsonLine 服务器相应的JSON数据（一行）
     * @return 处理完JSON数据后得到的机器人的回复
     */
    public static String parseJson(String jsonLine) {
        StringBuilder parsedContent = new StringBuilder();  // 储存解析后的内容

        try {
            jsonLine = "{" + jsonLine + "}";
            JSONObject data = new JSONObject(jsonLine).getJSONObject("data");

            // 处理服务器相应完成或服务器响应异常的情况
            String event = data.getString("event");
            if (event.equals("done")) return null;
            if (event.equals("error")) return data.getJSONObject("error_information").getString("err_msg") + "\n";

            JSONObject message = data.getJSONObject("message");
            if (data.getBoolean("is_finish")) {
                if (!message.getString("type").equals("follow_up")) {
                    return "\n";
                }
                return "建议问题：" + message.getString("content") + "\n";
            } else {
                String content = message.getString("content");
                parsedContent.append(content);
            }
        } catch (JSONException e) {
            return null;
        }

        return parsedContent.toString();
    }

    public static JSONObject creatJsonObject(String role, String type, String content, String content_type) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("role", role);
        jsonObject.put("type", type);
        jsonObject.put("content", content);
        jsonObject.put("content_type", content_type);
        return jsonObject;
    }

    public static JSONObject creatJsonObject(String role, String content, String content_type) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("role", role);
        jsonObject.put("content", content);
        jsonObject.put("content_type", content_type);
        return jsonObject;
    }

    public static JSONObject creatJsonObject(String conversation_id, String bot_id, String user, String query, Boolean stream, JSONArray chat_history) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("conversation_id", conversation_id);
        jsonObject.put("bot_id", bot_id);
        jsonObject.put("user", user);
        jsonObject.put("query", query);
        jsonObject.put("stream", stream);
        jsonObject.put("chat_history", chat_history);
        return jsonObject;
    }
}

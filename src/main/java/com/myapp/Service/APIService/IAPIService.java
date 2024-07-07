package com.myapp.Service.APIService;


import com.myapp.Model.APIResponse;

import org.json.JSONArray;

/**
 * 定义API服务接口，用于访问聊天功能。
 * 当application.yml中设置了active: test时，调用测试的MockApiService实现类。
 * 若设置了active: prod，则调用APIService实现类。
 * @author milk
 */
public interface IAPIService {
    /**
     * 访问API并处理用户输入，返回一个包含SseEmitter和消息的APIResponse对象。
     *
     * @param userInput      用户输入的字符串。
     * @param chatHistory    当前会话的聊天历史JSONArray。
     * @param username       当前用户的用户名。
     * @param conversation_id 当前会话的ID。
     * @return APIResponse 包含SseEmitter和消息的API响应对象。
     */
    APIResponse accessAPI(String userInput, JSONArray chatHistory, String username, String conversation_id);
}

package com.myapp.Service.APIService;

import com.myapp.Model.APIResponse;
import org.json.JSONArray;

public interface IAPIService {
    APIResponse accessAPI(String userInput, JSONArray chatHistory, String username, String conversation_id);
}

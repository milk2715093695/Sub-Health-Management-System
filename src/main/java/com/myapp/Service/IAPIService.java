package com.myapp.Service;

import com.myapp.model.APIResponse;
import org.json.JSONArray;

public interface IAPIService {
    APIResponse accessAPI(String userInput, JSONArray chatHistory, String username, String conversation_id);
}

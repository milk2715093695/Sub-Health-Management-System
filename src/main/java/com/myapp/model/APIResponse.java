package com.myapp.model;

import org.json.JSONObject;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class APIResponse {
    private final SseEmitter sseEmitter;
    private final JSONObject message;

    public APIResponse(SseEmitter sseEmitter, JSONObject message) {
        this.sseEmitter = sseEmitter;
        this.message = message;
    }

    public SseEmitter getSseEmitter() {
        return sseEmitter;
    }

    public JSONObject getMessage() {
        return message;
    }
}

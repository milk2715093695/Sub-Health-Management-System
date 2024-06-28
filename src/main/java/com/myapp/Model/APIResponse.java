package com.myapp.Model;

import org.json.JSONObject;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public record APIResponse(SseEmitter sseEmitter, JSONObject message) {}
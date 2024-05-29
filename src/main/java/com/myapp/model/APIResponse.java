package com.myapp.model;

import org.json.JSONObject;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

// 此类是为了方便后端使用
public record APIResponse(SseEmitter sseEmitter, JSONObject message) {}
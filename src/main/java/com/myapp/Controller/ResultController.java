package com.myapp.Controller;


import com.myapp.Service.SurveyService;
import com.myapp.entity.Survey;
import com.myapp.entity.User;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ResultController {
    SurveyService surveyService;
    @Autowired
    public ResultController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping("/result")
    public Map<String, Object> result(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
            Map<String, String> errMessage = new HashMap<>();
            errMessage.put("zh-CN", "你还未登录");
            errMessage.put("en", "You are not logged in");
            result.put("errMessage", errMessage);
            result.put("success", false);
            return result;
        }
        Survey survey = surveyService.getSurvey(user.getUsername());
        if (survey == null) {
            Map<String, String> errMessage = new HashMap<>();
            errMessage.put("zh-CN", "你还没有填写过问卷");
            errMessage.put("en", "You have not done any survey");
            result.put("errMessage", errMessage);
            result.put("success", false);
            return result;
        }
        result.put("success", true);
        result.put("survey", survey);
        return result;
    }
}

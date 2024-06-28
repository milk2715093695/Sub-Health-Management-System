package com.myapp.Controller;


import com.myapp.Service.AddErrorMessage;
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
    private final SurveyService surveyService;
    private final AddErrorMessage addErrorMessage;

    @Autowired
    public ResultController(SurveyService surveyService, AddErrorMessage addErrorMessage) {
        this.surveyService = surveyService;
        this.addErrorMessage = addErrorMessage;
    }

    @GetMapping("/result")
    public Map<String, Object> result(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
            addErrorMessage.addErrorMessage(result, "你还未登录", "You are not logged in");
            return result;
        }
        Survey survey = surveyService.getSurvey(user.getUsername());
        if (survey == null) {
            addErrorMessage.addErrorMessage(result, "你还没有填写过问卷", "You have not done any survey");
            return result;
        }
        result.put("success", true);
        result.put("survey", survey);
        return result;
    }
}

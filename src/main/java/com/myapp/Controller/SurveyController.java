package com.myapp.Controller;

import com.myapp.Service.SurveyService;
import com.myapp.entity.Survey;
import com.myapp.entity.User;
import com.myapp.model.HealthScore;
import com.myapp.model.SurveyData;


import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SurveyController {
    SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping("/healthScore")
    public Map<String, Object> healthScore(HttpSession session, @RequestBody HealthScore healthScore) {
        System.out.println(healthScore.gender());
        System.out.println(healthScore.score());
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
        SurveyData surveyData = new SurveyData(user.getUsername(), healthScore.gender(), healthScore.score(), null, null);
        Boolean success = surveyService.saveSurvey(new Survey(surveyData));
        result.put("success", success);
        if (!success) {
            Map<String, String> errMessage = new HashMap<>();
            errMessage.put("zh-CN", "保存失败");
            errMessage.put("en", "failed to save survey");
            result.put("errMessage", errMessage);
        }
        return result;
    }
}

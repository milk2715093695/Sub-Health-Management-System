package com.myapp.Controller;

import com.myapp.Service.SurveyService;
import com.myapp.entity.Survey;
import com.myapp.entity.User;
import com.myapp.model.HealthScore;
import com.myapp.model.SurveyData;

import com.myapp.model.SurveyScore;
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
        if (!checkUser(session, result, user)) return result;

        SurveyData surveyData = new SurveyData(user.getUsername(), healthScore.gender(), healthScore.score(), null, null);
        return saveSurveyScore(result, surveyData);
    }

    @PostMapping("/mentalScore")
    public Map<String, Object> mentalScore(HttpSession session, @RequestBody SurveyScore surveyScore) {
        System.out.println(surveyScore.score());
        Map<String, Object> result = new HashMap<>();

        User user = (User) session.getAttribute("user");
        if (!checkUser(session, result, user)) return result;

        SurveyData surveyData = new SurveyData(user.getUsername(), null, null, surveyScore.score(), null);
        return saveSurveyScore(result, surveyData);
    }

    @PostMapping("/riskScore")
    public Map<String, Object> riskScore(HttpSession session, @RequestBody SurveyScore surveyScore) {
        System.out.println(surveyScore.score());
        Map<String, Object> result = new HashMap<>();

        User user = (User) session.getAttribute("user");
        if (!checkUser(session, result, user)) return result;

        SurveyData surveyData = new SurveyData(user.getUsername(), null, null, null, surveyScore.score());
        return saveSurveyScore(result, surveyData);
    }

    private Boolean checkUser(HttpSession session, Map<String, Object> result, User user) {
        if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
            Map<String, String> errMessage = new HashMap<>();
            errMessage.put("zh-CN", "你还未登录");
            errMessage.put("en", "You are not logged in");
            result.put("errMessage", errMessage);
            result.put("success", false);
            return false;
        }
        return true;
    }

    private Map<String, Object> saveSurveyScore(Map<String, Object> result, SurveyData surveyData) {
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

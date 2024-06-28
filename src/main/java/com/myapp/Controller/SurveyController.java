package com.myapp.Controller;


import com.myapp.Service.AddErrorMessage;
import com.myapp.Service.SurveyService;
import com.myapp.entity.Survey;
import com.myapp.entity.User;
import com.myapp.model.HealthScore;
import com.myapp.model.SurveyData;
import com.myapp.model.SurveyScore;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理问卷相关请求的控制器
 * @author milk
 */
@RestController
public class SurveyController {
    private final SurveyService surveyService;
    private final AddErrorMessage addErrorMessage;

    /**
     * 通过自动注入构建SurveyController的实例。
     *
     * @param surveyService     用于处理问卷操作的服务。
     * @param addErrorMessage   用于添加错误消息的服务。
     */
    @Autowired
    public SurveyController(SurveyService surveyService, AddErrorMessage addErrorMessage) {
        this.surveyService = surveyService;
        this.addErrorMessage = addErrorMessage;
    }

    /**
     * 处理/healthScore的POST请求，保存用户的健康得分。
     *
     * @param session      包含用户会话信息的HttpSession
     * @param healthScore  由RequestBody注解解析的请求体，包含用户的健康得分。
     * @return             包含结果消息的Map对象。如果保存成功，则结果为"success"。
     */
    @PostMapping("/healthScore")
    public Map<String, Object> healthScore(HttpSession session, @RequestBody HealthScore healthScore) {
        Map<String, Object> result = new HashMap<>();

        User user = (User) session.getAttribute("user");
        if (!checkUser(session, result, user)) return result;

        SurveyData surveyData = new SurveyData(user.getUsername(), healthScore.gender(), healthScore.score(), null, null);
        return saveSurveyScore(result, surveyData);
    }

    /**
     * 处理/mentalScore的POST请求，保存用户的心理得分。
     *
     * @param session      包含用户会话信息的HttpSession
     * @param surveyScore  由RequestBody注解解析的请求体，包含用户的心理得分。
     * @return             包含结果消息的Map对象。如果保存成功，则结果为"success"。
     */
    @PostMapping("/mentalScore")
    public Map<String, Object> mentalScore(HttpSession session, @RequestBody SurveyScore surveyScore) {
        Map<String, Object> result = new HashMap<>();

        User user = (User) session.getAttribute("user");
        if (!checkUser(session, result, user)) return result;

        SurveyData surveyData = new SurveyData(user.getUsername(), null, null, surveyScore.score(), null);
        return saveSurveyScore(result, surveyData);
    }

    /**
     * 处理/mentalScore的POST请求，保存用户的风险得分。
     *
     * @param session      包含用户会话信息的HttpSession
     * @param surveyScore  由RequestBody注解解析的请求体，包含用户的心理得分。
     * @return             包含结果消息的Map对象。如果保存成功，则结果为"success"。
     */
    @PostMapping("/riskScore")
    public Map<String, Object> riskScore(HttpSession session, @RequestBody SurveyScore surveyScore) {
        Map<String, Object> result = new HashMap<>();

        User user = (User) session.getAttribute("user");
        if (!checkUser(session, result, user)) return result;

        SurveyData surveyData = new SurveyData(user.getUsername(), null, null, null, surveyScore.score());
        return saveSurveyScore(result, surveyData);
    }

    /**
     * 处理/result的GET请求，返回用户的问卷结果。
     *
     * @param session HttpSession，包含用户会话信息。
     * @return 包含问卷结果的Map对象。如果用户未登录，会包含错误信息。
     */
    @GetMapping("/result")
    public Map<String, Object> result(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
            addErrorMessage.addErrorMessage(result, "你还未登录", "You are not logged in");
            return result;
        }

        Survey survey = surveyService.getSurvey(user.getUsername());
        if (survey == null || survey.getHealthScore() == null || survey.getMentalScore() == null || survey.getRiskScore() == null) {
            addErrorMessage.addErrorMessage(result, "你还没有填写完问卷", "You have not done any survey");
            return result;
        }

        result.put("success", true);
        result.put("survey", survey);
        return result;
    }

    /**
     * 检查用户是否存在并已登录。如果用户没登录，添加验一个“没有登录”的错误消息。
     *
     * @param session 包含用户会话信息的HttpSession。
     * @param result  用于存储结果或错误消息的Map。
     * @param user    要检验的User对象。
     * @return        如果用户已登录，返回true；否则，返回false。
     */
    private Boolean checkUser(HttpSession session, Map<String, Object> result, User user) {
        if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
            addErrorMessage.addErrorMessage(result, "你还未登录", "You are not logged in");
        }
        return true;
    }

    /**
     * 保存用户的问卷得分。
     *
     * @param result     用于存储结果消息的Map对象。
     * @param surveyData 包含用户问卷得分的对象。
     * @return           包含结果消息的Map对象。如果保存成功，则结果为"success"。
     */
    private Map<String, Object> saveSurveyScore(Map<String, Object> result, SurveyData surveyData) {
        Boolean success = surveyService.saveSurvey(new Survey(surveyData));
        if (!success) {
            addErrorMessage.addErrorMessage(result, "保存失败", "failed to save survey");
        } else {
            result.put("success", true);
        }
        return result;
    }
}

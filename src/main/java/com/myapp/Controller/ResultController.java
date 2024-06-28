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

/**
 * 处理显示问卷请求的控制器
 * @author milk
 */
@RestController
public class ResultController {
    private final SurveyService surveyService;
    private final AddErrorMessage addErrorMessage;

    /**
     * 构造函数，通过@Autowired自动注入所需的服务。
     *
     * @param surveyService 调查服务，提供调查信息。
     * @param addErrorMessage 错误消息服务，负责添加错误消息。
     */
    @Autowired
    public ResultController(SurveyService surveyService, AddErrorMessage addErrorMessage) {
        this.surveyService = surveyService;
        this.addErrorMessage = addErrorMessage;
    }

    /**
     * 处理/result的GET请求，返回用户的调查结果。
     *
     * @param session HttpSession，包含用户会话信息。
     * @return 包含调查结果的Map对象。如果用户未登录，会包含错误信息。
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
}

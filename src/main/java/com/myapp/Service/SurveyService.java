package com.myapp.Service;

import com.myapp.entity.Survey;
import com.myapp.repository.SurveyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;

    // 自动注入实例（这样可以不需要手动创建实例）
    @Autowired
    SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    /**
     * 此方法用于保存问卷数据。
     * <ul>
     * <li>如果该用户的问卷已经存在：</li>
     * <ul>
     * <li>如果新的问卷数据的性别与已有的不同，返回false，不保存数据。</li>
     * <li>否则，更新并保存已有的问卷数据的健康分数、心理健康分数和风险分数。</li>
     * </ul>
     * <li>如果该用户不存在已有的问卷数据，直接保存调查数据。</li>
     * </ul>
     *
     * @param survey - 需要保存的调查数据
     *
     * @return 如果保存成功返回true，否则返回false。
     */
    public Boolean saveSurvey(Survey survey) {
        Survey existingSurvey = surveyRepository.findByUsername(survey.getUsername());

        if (existingSurvey != null) {
            if (!Objects.equals(survey.getGender(), existingSurvey.getGender())) return false;

            if(survey.getHealthScore() != null) existingSurvey.setHealthScore(survey.getHealthScore());
            if(survey.getMentalScore() != null) existingSurvey.setMentalScore(survey.getMentalScore());
            if(survey.getRiskScore() != null) existingSurvey.setRiskScore(survey.getRiskScore());
            surveyRepository.save(existingSurvey);
        } else {
            surveyRepository.save(survey);
        }

        return true;
    }

    /**
     * getSurvey方法用于根据用户名返回先前的问卷数据
     *
     * @param username 用户名
     *
     * @return 该用户以前的问卷记录，若不存在返回null
     */
    public Survey getSurvey(String username) {
        return surveyRepository.findByUsername(username);
    }
}

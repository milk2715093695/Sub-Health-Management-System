package com.myapp.Service;

import com.myapp.entity.Survey;
import com.myapp.repository.SurveyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;

    @Autowired
    SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

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

    public Survey getSurvey(String username) {
        return surveyRepository.findByUsername(username);
    }
}

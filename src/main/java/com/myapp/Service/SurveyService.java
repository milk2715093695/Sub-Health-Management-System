package com.myapp.Service;

import com.myapp.entity.Survey;
import com.myapp.repository.SurveyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            surveyRepository.delete(existingSurvey);
            surveyRepository.save(survey);
            return true;
        } else {
            surveyRepository.save(survey);
            return false;
        }
    }

    public Survey getSurvey(String username) {
        return surveyRepository.findByUsername(username);
    }
}

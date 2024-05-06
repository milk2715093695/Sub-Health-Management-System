package com.myapp.repository;

import com.myapp.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Survey findByUsername(String username);
}

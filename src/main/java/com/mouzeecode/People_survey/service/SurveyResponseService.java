package com.mouzeecode.People_survey.service;

import com.mouzeecode.People_survey.dto.SurveySummaryDTO;
import com.mouzeecode.People_survey.entity.UserDetails;

import java.util.List;

public interface SurveyResponseService {

    UserDetails saveResponse(UserDetails response);
    List<UserDetails> getAllResponses();
    UserDetails getResponseById(Long id);
    UserDetails deleteResponse(Long id);
    SurveySummaryDTO getSummary();

}

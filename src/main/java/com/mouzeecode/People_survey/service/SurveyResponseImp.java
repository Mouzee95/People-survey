package com.mouzeecode.People_survey.service;

import com.mouzeecode.People_survey.entity.UserDetails;
import com.mouzeecode.People_survey.repo.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SurveyResponseImp implements SurveyResponseService {

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyResponseImp(SurveyRepository surveyRepository) {

        this.surveyRepository = surveyRepository;
    }

    @Override
    public UserDetails saveResponse(UserDetails response){
        return surveyRepository.save(response);
    }

    @Override
    public List<UserDetails> getAllResponses() {
        return surveyRepository.findAll();
    }

    @Override
    public UserDetails getResponseById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Not found by id: " + id));
    }

    @Override
    public UserDetails deleteResponse(Long id) {
        surveyRepository.deleteById(id);
        return deleteResponse(id);
    }


}

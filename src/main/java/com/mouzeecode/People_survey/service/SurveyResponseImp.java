package com.mouzeecode.People_survey.service;

import com.mouzeecode.People_survey.entity.UserDetails;
import com.mouzeecode.People_survey.exception.ResourceNotFoundException;
import com.mouzeecode.People_survey.repo.SurveyRepository;
import org.springframework.transaction.annotation.Transactional;
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
                .orElseThrow(()->new ResourceNotFoundException("Not found by id: " + id));
    }

    @Override
    @Transactional
    public UserDetails deleteResponse(Long id) {
        UserDetails user = getResponseById(id); // Safely fetch first
        surveyRepository.deleteById(id);        // Then delete
        return user;                            // Return deleted user
    }





}

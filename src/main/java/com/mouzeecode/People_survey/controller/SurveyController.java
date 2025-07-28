package com.mouzeecode.People_survey.controller;

import com.mouzeecode.People_survey.entity.UserDetails;
import com.mouzeecode.People_survey.service.SurveyResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveyform")
public class SurveyController {

    @Autowired
    private SurveyResponseService surveyResponseService;

    @PostMapping("/add")
    public ResponseEntity<UserDetails> createSurvey(@Valid @RequestBody UserDetails response){
        return ResponseEntity.ok(surveyResponseService.saveResponse(response));

    }
    @GetMapping("/all")
    public List<UserDetails> getAll(){
        return surveyResponseService.getAllResponses();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDetails> getById(@PathVariable Long id){

        return ResponseEntity.ok(surveyResponseService.getResponseById(id));

    }
    @DeleteMapping("/{id}")
    public  UserDetails deleteById(@PathVariable Long id){
        return surveyResponseService.deleteResponse(id);

    }
}

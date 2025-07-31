package com.mouzeecode.People_survey.controller;

import com.mouzeecode.People_survey.dto.SurveySummaryDTO;
import com.mouzeecode.People_survey.entity.UserDetails;
import com.mouzeecode.People_survey.exception.ResourceNotFoundException;
import com.mouzeecode.People_survey.service.SurveyResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surveyform")
@CrossOrigin(origins = "http://localhost:4200")
public class SurveyController {

    @Autowired
    private SurveyResponseService surveyResponseService;

    @PostMapping("/add")
    public ResponseEntity<UserDetails> createSurvey(@Valid @RequestBody UserDetails response){
        return ResponseEntity.ok(surveyResponseService.saveResponse(response));

    }
//    @GetMapping("/all")
//    public List<UserDetails> getAll(){
//        return surveyResponseService.getAllResponses();
//    }
    @GetMapping("/summary")
    public SurveySummaryDTO getSummary() {
        return surveyResponseService.getSummary();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetails> getById(@PathVariable Long id){

        return ResponseEntity.ok(surveyResponseService.getResponseById(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try{
            UserDetails deleted = surveyResponseService.deleteResponse(id);
            return ResponseEntity.ok("Deleted Successfully");
        } catch (ResourceNotFoundException ex){
            return ResponseEntity.status(404).body("User with id " + id + " Not found");
        }

    }

}

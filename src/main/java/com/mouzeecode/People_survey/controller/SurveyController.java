package com.mouzeecode.People_survey.controller;

import com.mouzeecode.People_survey.entity.UserDetails;
import com.mouzeecode.People_survey.service.SurveyResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
    public ResponseEntity<UserDetails> deleteById(@PathVariable Long id) {
        UserDetails deleted = surveyResponseService.deleteResponse(id);
        return ResponseEntity.ok(deleted);
    }

//    @GetMapping("/results")
//    public String showResults(Model model) {
//        model.addAttribute("total", surveyResponseService.getTotalSurveys());
//        model.addAttribute("avgAge", surveyResponseService.getAverageAge());
//        model.addAttribute("oldest", surveyResponseService.getOldestAge());
//        model.addAttribute("youngest", surveyResponseService.getYoungestAge());
//        model.addAttribute("pizzaPercent", surveyResponseService.getPizzaPercentage());
//        model.addAttribute("avgEatOut", surveyResponseService.getAverageEatOutRating());
//        return "survey_results";
//    }


}

package com.mouzeecode.People_survey.service;

import com.mouzeecode.People_survey.dto.SurveySummaryDTO;
import com.mouzeecode.People_survey.entity.UserDetails;
import com.mouzeecode.People_survey.exception.ResourceNotFoundException;
import com.mouzeecode.People_survey.repo.SurveyRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    @Override
    public SurveySummaryDTO getSummary() {
        List<UserDetails> responses = surveyRepository.findAll();
        SurveySummaryDTO summary = new SurveySummaryDTO();

        int total = responses.size();
        summary.setTotalSurveys(total);

        if (total == 0) return summary;

        List<Integer> ages = responses.stream()
                .map(r -> Period.between(r.getDob(), LocalDate.now()).getYears())
                .collect(Collectors.toList());

        summary.setAverageAge(ages.stream().mapToInt(Integer::intValue).average().orElse(0));
        summary.setMinAge(Collections.min(ages));
        summary.setMaxAge(Collections.max(ages));

        Map<String, Long> foodCounts = responses.stream()
                .flatMap(r -> r.getFavoriteFoods().stream())
                .collect(Collectors.groupingBy(f -> f, Collectors.counting()));

        Map<String, Double> foodPercentages = foodCounts.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Math.round((e.getValue() * 100.0 / total) * 10.0) / 10.0
                ));

        summary.setFoodPercentages(foodPercentages);

        summary.setAvgWatchMovies(avg(responses, "watchMovies"));
        summary.setAvgListenRadio(avg(responses, "listenRadio"));
        summary.setAvgEatOut(avg(responses, "eatOut"));
        summary.setAvgWatchTV(avg(responses, "watchTV"));

        return summary;
    }

    private double avg(List<UserDetails> responses, String field) {
        return Math.round(
                responses.stream()
                        .mapToInt(r -> switch (field) {
                            case "watchMovies" -> r.getWatchMovies();
                            case "listenRadio" -> r.getListenRadio();
                            case "eatOut" -> r.getEatOut();
                            case "watchTV" -> r.getWatchTV();
                            default -> 0;
                        })
                        .average().orElse(0) * 10.0
        ) / 10.0;
    }
}

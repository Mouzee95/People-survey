package com.mouzeecode.People_survey.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SurveySummaryDTO {
    private long totalSurveys;
    private double averageAge;
    private int minAge;
    private int maxAge;

    private Map<String, Double> foodPercentages;

    private double avgWatchMovies;
    private double avgListenRadio;
    private double avgEatOut;
    private double avgWatchTV;
}

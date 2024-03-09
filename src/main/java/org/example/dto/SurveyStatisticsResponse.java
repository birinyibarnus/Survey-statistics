package org.example.dto;

import lombok.Builder;

@Builder
public record SurveyStatisticsResponse(
        long surveyId,
        String surveyName,
        long numOfCompleted,
        long numOfFiltered,
        long numOfRejected,
        double avgTimeSpent
) {
}

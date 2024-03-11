package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.DataStore;
import org.example.dto.SurveyStatisticsResponse;
import org.example.entity.Member;
import org.example.entity.Participation;
import org.example.entity.Survey;
import org.example.service.DataService;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RequiredArgsConstructor
public class DataServiceImpl implements DataService {

    private final DataStore dataStore;

    private Stream<Participation> getCompletedParticipations() {
        return dataStore.getParticipations().stream()
                .filter(participation -> participation.getStatus() == 4);
    }

    // 2.a
    @Override
    public Stream<Member> getRespondentsWithCompletedStatus(int surveyId) {
        return getCompletedParticipations()
                .filter(participation -> participation.getSurveyId() == surveyId)
                .mapToInt(Participation::getMemberId)
                .distinct()
                .filter(dataStore.getMembers()::containsKey)
                .mapToObj(dataStore.getMembers()::get);
    }

    // 2.b
    @Override
    public Stream<Survey> getCompletedSurveysByMemberId(int memberId) {
        return getCompletedParticipations()
                .filter(participation -> participation.getMemberId() == memberId)
                .mapToInt(Participation::getSurveyId)
                .distinct()
                .filter(dataStore.getSurveys()::containsKey)
                .mapToObj(dataStore.getSurveys()::get);
    }

    // 2.c
    @Override
    public Map<Integer, Integer> getCollectedPointsByMember(int memberId) {
        return dataStore.getParticipations().stream()
                .filter(participation -> participation.getMemberId() == memberId)
                .mapToInt(Participation::getSurveyId)
                .filter(dataStore.getSurveys()::containsKey)
                .mapToObj(dataStore.getSurveys()::get)
                .collect(Collectors.groupingBy(
                        Survey::getSurveyId,
                        Collectors.summingInt(Survey::getCompletionPoints)));
    }

    // 2.d
    @Override
    public Stream<Member> getInvitedMembersForSurvey(int surveyId) {
        return dataStore.getParticipations().stream()
                .filter(participation -> participation.getStatus() <= 2
                        && participation.getSurveyId() == surveyId)
                .mapToInt(Participation::getMemberId)
                .mapToObj(dataStore.getMembers()::get)
                .filter(Member::isActive);
    }

    // 2.e
    @Override
    public Stream<SurveyStatisticsResponse> getSurveyStatistics() {
        return dataStore.getSurveys().values().stream()
                .map(survey -> {
                    Map<Integer, Long> statusCounts = dataStore.getParticipations().stream()
                            .filter(participation -> participation.getSurveyId() == survey.getSurveyId())
                            .collect(Collectors.groupingBy(Participation::getStatus, Collectors.counting()));
                    long completedStatus = statusCounts.getOrDefault(4, 0L);
                    long filteredStatus = statusCounts.getOrDefault(3, 0L);
                    long rejectedStatus = statusCounts.getOrDefault(2, 0L);
                    double averageTimeSpent = dataStore.getParticipations().stream()
                            .filter(participation -> participation.getSurveyId() == survey.getSurveyId())
                            .mapToDouble(Participation::getLength)
                            .average()
                            .orElse(0.0);
                    return SurveyStatisticsResponse.builder()
                            .surveyId(survey.getSurveyId())
                            .surveyName(survey.getName())
                            .numOfCompleted(completedStatus)
                            .numOfFiltered(filteredStatus)
                            .numOfRejected(rejectedStatus)
                            .avgTimeSpent(averageTimeSpent)
                            .build();
                });
    }
}

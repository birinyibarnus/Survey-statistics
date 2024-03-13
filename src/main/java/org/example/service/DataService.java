package org.example.service;

import org.example.dto.SurveyStatisticsResponse;
import org.example.entity.Member;
import org.example.entity.Survey;

import java.util.Map;
import java.util.stream.Stream;

public interface DataService {
    // 2.a
    Stream<Member> getRespondentsWithCompletedStatus(int surveyId);

    // 2.b
    Stream<Survey> getCompletedSurveysByMemberId(int memberId);

    //2.c
    Map<Integer, Integer> getCollectedPointsByMember(int memberId);

    //2.d
    Stream<Member> getInvitableMember(int surveyId);

    //2.e
    Stream<SurveyStatisticsResponse> getSurveyStatistics();
}

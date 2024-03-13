import org.example.DataStore;
import org.example.entity.Member;
import org.example.entity.Participation;
import org.example.entity.Survey;
import org.example.service.impl.DataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DataServiceTest {

    @Mock
    private DataStore dataStoreMock;

    @InjectMocks
    private DataServiceImpl underTest;

    @Test
    void getRespondentsWithCompletedStatusShouldReturnMemberStream() {
        // given
        HashMap<Integer, Member> members = new HashMap<>();
        Member member = Member.builder().memberId(1).build();
        members.put(1, member);
        Participation participation = Participation.builder().memberId(1).status(4).surveyId(1).build();
        List<Participation> participationList = List.of(participation);
        given(dataStoreMock.getMembers()).willReturn(members);
        given(dataStoreMock.getParticipations()).willReturn(participationList);
        // when
        Stream<Member> actual = underTest.getRespondentsWithCompletedStatus(1);
        // then
        assertTrue(actual.anyMatch(memberItem -> memberItem.getMemberId() == 1));
    }

    @Test
    void getCompletedSurveysByMemberIdShouldReturnSurveyStream() {
        // given
        Participation participation = Participation.builder().memberId(1).status(4).surveyId(1).build();
        List<Participation> participationList = List.of(participation);
        given(dataStoreMock.getParticipations()).willReturn(participationList);
        Survey survey = Survey.builder().surveyId(1).build();
        HashMap<Integer, Survey> surveys = new HashMap<>();
        surveys.put(1, survey);
        given(dataStoreMock.getSurveys()).willReturn(surveys);
        // when
        Stream<Survey> actual = underTest.getCompletedSurveysByMemberId(1);
        // then
        assertTrue(actual.anyMatch(surveyItem -> surveyItem.getSurveyId() == 1));
    }

    @Test
    void getCollectedPointsByMemberShouldReturnMapOfPoints() {
        // given
        Participation participation = Participation.builder().surveyId(1).memberId(1).build();
        List<Participation> participations = List.of(participation);
        given(dataStoreMock.getParticipations()).willReturn(participations);
        Survey survey = Survey.builder().surveyId(1).completionPoints(1).build();
        HashMap<Integer, Survey> surveys = new HashMap<>();
        surveys.put(1, survey);
        given(dataStoreMock.getSurveys()).willReturn(surveys);
        // when
        Map<Integer, Integer> actual = underTest.getCollectedPointsByMember(1);
        // then
        assertTrue(actual.containsKey(1));
        assertTrue(actual.containsValue(1));
    }

    @Test
    void getInvitedMembersForSurveyShouldReturnMemberStream() {
        // given
        Participation participation = Participation.builder().status(2).surveyId(1).memberId(1).build();
        List<Participation> participations = List.of(participation);
        given(dataStoreMock.getParticipations()).willReturn(participations);
        Member member = Member.builder().isActive(true).memberId(1).build();
        HashMap<Integer, Member> members = new HashMap<>();
        members.put(1, member);
        given(dataStoreMock.getMembers()).willReturn(members);
        // when
        Stream<Member> actual = underTest.getInvitableMember(1);
        // then
        assertTrue(actual.anyMatch(memberItem -> memberItem.getMemberId() == 1));
    }
}

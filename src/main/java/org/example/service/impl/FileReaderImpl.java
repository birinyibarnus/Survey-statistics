package org.example.service.impl;

import org.example.entity.Member;
import org.example.entity.Participation;
import org.example.entity.Status;
import org.example.entity.Survey;
import org.example.service.FileReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FileReaderImpl implements FileReader {

    @Override
    public HashMap<Integer, Survey> readSurveys() {
        String surveysPath = "files/OO - 2 - Surveys.csv";
        HashMap<Integer, Survey> surveys = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(surveysPath));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] elements = scanner.nextLine().split(",");
                Survey survey = Survey.builder()
                        .surveyId(Integer.parseInt(elements[0]))
                        .name(elements[1])
                        .expectedCompletes(Integer.parseInt(elements[2]))
                        .completionPoints(Integer.parseInt(elements[3]))
                        .filteredPoints(Integer.parseInt(elements[4]))
                        .build();
                surveys.put(survey.getSurveyId(), survey);
            }
            return surveys;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<Integer, Status> readStatuses() {
        String statusesPath = "files/OO - 2 - Statuses.csv";
        HashMap<Integer, Status> statuses = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(statusesPath));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] elements = scanner.nextLine().split(",");
                Status status = Status.builder()
                        .statusId(Integer.parseInt(elements[0]))
                        .name(elements[1])
                        .build();
                statuses.put(status.getStatusId(), status);
            }
            return statuses;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<Integer, Member> readMembers() {
        String memberPath = "files/OO - 2 - Members.csv";
        HashMap<Integer, Member> members = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File(memberPath));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] elements = scanner.nextLine().split(",");
                Member member = Member.builder()
                        .memberId(Integer.parseInt(elements[0]))
                        .fullName(elements[1])
                        .emailAddress(elements[2])
                        .isActive(Integer.parseInt(elements[3]) == 1)
                        .build();
                members.put(member.getMemberId(), member);
            }
            return members;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Participation> readParticipations() {
        String participationPath = "files/OO - 2 - Participation.csv";
        List<Participation> participation = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(participationPath));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] elements = scanner.nextLine().split(",");
                Integer length = (elements.length > 3 && !elements[3].isEmpty()) ? Integer.parseInt(elements[3]) : 0;
                Participation participationToAdd = Participation.builder()
                        .memberId(Integer.parseInt(elements[0]))
                        .surveyId(Integer.parseInt(elements[1]))
                        .status(Integer.parseInt(elements[2]))
                        .length(length)
                        .build();
                participation.add(participationToAdd);
            }
            return participation;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

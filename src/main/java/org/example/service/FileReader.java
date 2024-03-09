package org.example.service;

import org.example.entity.Member;
import org.example.entity.Participation;
import org.example.entity.Status;
import org.example.entity.Survey;

import java.util.HashMap;
import java.util.List;

public interface FileReader {
    HashMap<Integer, Survey> readSurveys();

    HashMap<Integer, Status> readStatuses();

    HashMap<Integer, Member> readMembers();

    List<Participation> readParticipations();
}

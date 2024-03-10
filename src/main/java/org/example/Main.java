package org.example;

import org.example.service.DataService;
import org.example.service.impl.DataServiceImpl;
import org.example.service.impl.FileReaderImpl;

public class Main {
    public static void main(String[] args) {
        FileReaderImpl fileReader = new FileReaderImpl();
        DataStore dataStore = DataStore.getDataStore(fileReader);
        DataService dataService = new DataServiceImpl(dataStore);
        System.out.println("Survey Statistics");
        dataService.getSurveyStatistics().forEach(System.out::println);
        System.out.println("\nCompleted Surveys");
        dataService.getCompletedSurveysByMemberId(1).forEach(System.out::println);
        System.out.println("\nCollected Points by member\nSurvey ID - Collected points");
        dataService.getCollectedPointsByMember(1).entrySet().forEach(System.out::println);
        System.out.println("\nInvited members for survey");
        dataService.getInvitedMembersForSurvey(1).forEach(System.out::println);
        System.out.println("\nRespondents with completed status");
        dataService.getRespondentsWithCompletedStatus(1).forEach(System.out::println);
    }
}
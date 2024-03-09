package org.example;

import org.example.dto.SurveyStatisticsResponse;
import org.example.service.impl.DataServiceImpl;
import org.example.service.impl.FileReaderImpl;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        FileReaderImpl fileReader = new FileReaderImpl();
        DataStore dataStore = DataStore.getDataStore(fileReader);
        DataServiceImpl dataService = new DataServiceImpl(dataStore);

        Stream<SurveyStatisticsResponse> allRespondentsWithCompletedStatus = dataService.getSurveyStatistics();
        allRespondentsWithCompletedStatus.forEach(System.out::println);
    }
}
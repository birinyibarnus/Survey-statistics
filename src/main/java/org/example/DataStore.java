package org.example;

import lombok.Getter;
import org.example.entity.Member;
import org.example.entity.Participation;
import org.example.entity.Status;
import org.example.entity.Survey;
import org.example.service.FileReader;

import java.util.HashMap;
import java.util.List;

public final class DataStore {

    private static DataStore DATASTORE;
    private final FileReader fileReader;
    @Getter(lazy = true)
    private final HashMap<Integer, Member> members = fileReader.readMembers();
    @Getter(lazy = true)
    private final List<Participation> participations = fileReader.readParticipations();
    @Getter(lazy = true)
    private final HashMap<Integer, Status> statuses = fileReader.readStatuses();
    @Getter(lazy = true)
    private final HashMap<Integer, Survey> surveys = fileReader.readSurveys();

    private DataStore(FileReader fileReader) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.fileReader = fileReader;
    }

    public static DataStore getDataStore(FileReader fileReader) {
        if (DATASTORE == null) {
            DATASTORE = new DataStore(fileReader);
        }
        return DATASTORE;
    }
}

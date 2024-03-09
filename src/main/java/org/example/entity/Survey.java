package org.example.entity;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class Survey {

    int surveyId;
    String name;
    int expectedCompletes;
    int completionPoints;
    int filteredPoints;
}

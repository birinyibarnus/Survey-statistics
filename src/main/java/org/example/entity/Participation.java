package org.example.entity;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class Participation {

    int memberId;
    int surveyId;
    int status;
    Integer length;
}

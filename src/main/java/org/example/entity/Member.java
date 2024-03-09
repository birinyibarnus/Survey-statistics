package org.example.entity;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class Member {

    int memberId;
    String fullName;
    String emailAddress;
    boolean isActive;
}

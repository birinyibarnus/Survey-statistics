package org.example.entity;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class Status {

    int statusId;
    String name;
}

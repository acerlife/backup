package com.makhnov.backup.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class BackUpInfo {
    private final String userName;
    private final int todoItemId;
    private final String subject;
    private final LocalDateTime dueDate;
    private final boolean done;
}

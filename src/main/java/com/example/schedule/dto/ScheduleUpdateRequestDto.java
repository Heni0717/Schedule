package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateRequestDto {
    private final int id;
    private final String updateTask;
    private final String updateUserName;
    private final String password;

}

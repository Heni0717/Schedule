package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String author;
    private String password;
    private String task;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.author = schedule.getAuthor();
        this.task = schedule.getTask();
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }
}

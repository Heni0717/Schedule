package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
//@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String author;
    private String task;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public ScheduleResponseDto(Long id, String author, String task, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.author = author;
        this.task = task;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}

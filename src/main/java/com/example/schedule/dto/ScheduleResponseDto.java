package com.example.schedule.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleResponseDto {

    private Long id;
    private String author;
    private String task;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}

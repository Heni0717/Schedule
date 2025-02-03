package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Schedule {

    private Long id;
    private String task;
    private String password;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Long userId;

}

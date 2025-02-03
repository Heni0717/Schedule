package com.example.schedule.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ScheduleResponseDto {

    private final int id;
    private final String task;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final int userId;
    private final String userName;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String getCreatedAt() {
        return createdAt != null ? createdAt.format(FORMATTER) : null;
    }

    public String getUpdatedAt() {
        return updatedAt != null ? updatedAt.format(FORMATTER) : null;
    }

}

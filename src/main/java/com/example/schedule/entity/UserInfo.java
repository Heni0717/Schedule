package com.example.schedule.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfo {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class UserInfoRequestDto {
    private final String name;
    private final String email;

    public UserInfoRequestDto(String name, String email){
        this.name = name;
        this.email = email;
    }
}

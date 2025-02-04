package com.example.schedule.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class UserInfoRequestDto {
    private final String name;

    @Email(message = "이메일 형식에 맞게 작성하세요")
    private final String email;

    public UserInfoRequestDto(String name, String email){
        this.name = name;
        this.email = email;
    }
}

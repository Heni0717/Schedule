package com.example.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    @NotBlank(message = "일정을 입력하세요")
    @Size(max = 200, message = "최대 200자까지 입력 가능")
    private final String task;

    @NotBlank(message = "비밀번호를 입력하세요")
    private final String password;
    private final int userId;

}

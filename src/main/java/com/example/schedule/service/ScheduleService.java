package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    void createSchedule(ScheduleRequestDto dto);
    List<ScheduleResponseDto> findAllSchedules();
    ScheduleResponseDto findScheduleById(int id);
    List<ScheduleResponseDto> findSchedulesByUserName(String userName);
    List<ScheduleResponseDto> findSchedulesByUpdatedDate(String updatedAt);

}

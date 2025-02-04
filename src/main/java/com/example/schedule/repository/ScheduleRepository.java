package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.util.List;
import java.util.Map;

public interface ScheduleRepository {

    int createSchedule(Schedule schedule);
    List<ScheduleResponseDto> findAllSchedules();
    ScheduleResponseDto findScheduleById(int id);
    List<ScheduleResponseDto> findSchedulesByUserName(String userName);
    List<ScheduleResponseDto> findSchedulesByUpdatedDate(String startDate, String endDate);
    Map<String, Object> checkPassword(int id);
    int updateScheduleTask(int id, String updateTask);
    int deleteSchedule(int id, String password);
}

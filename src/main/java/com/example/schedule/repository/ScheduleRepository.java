package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    int createSchedule(Schedule schedule);
    List<ScheduleResponseDto> findAllSchedules();
//    Schedule findScheduleByIdOrElseThrow(Long id);
//    Optional<Schedule> findScheduleById(Long id);

}

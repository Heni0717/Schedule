package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;

public interface ScheduleRepository {

    int createSchedule(Schedule schedule);
//    List<ScheduleResponseDto> findAllSchedules();
//    Schedule findScheduleByIdOrElseThrow(Long id);
//    Optional<Schedule> findScheduleById(Long id);

}

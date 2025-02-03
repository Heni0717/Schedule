package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 일정 생성
    @Transactional
    @Override
    public void createSchedule(ScheduleRequestDto dto) {
        Schedule schedule = Schedule.builder()
                .task(dto.getTask())
                .password(dto.getPassword())
                .userId((long) dto.getUserId())
                .build();
        scheduleRepository.createSchedule(schedule);
    }

    // 전체 일정 조회
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAllSchedules();
    }

    // 일정 id
    @Override
    public ScheduleResponseDto findScheduleById(int id) {
        return scheduleRepository.findScheduleById(id);
    }

    // 작성자
    @Override
    public List<ScheduleResponseDto> findSchedulesByUserName(String userName) {
        return scheduleRepository.findSchedulesByUserName(userName);
    }

    // 수정일
    @Override
    public List<ScheduleResponseDto> findSchedulesByUpdatedDate(String updatedAt) {
        return scheduleRepository.findSchedulesByUpdatedDate(updatedAt);
    }
}

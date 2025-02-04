package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.ScheduleUpdateRequestDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
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

    // 일정 조회 기능
    // 전체 조회
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAllSchedules();
    }

    // 단건 조회: id
    @Override
    public ScheduleResponseDto findScheduleById(int id) {
        return scheduleRepository.findScheduleById(id);
    }

    // 다건 조회: 작성자
    @Override
    public List<ScheduleResponseDto> findSchedulesByUserName(String userName) {
        return scheduleRepository.findSchedulesByUserName(userName);
    }

    // 다건 조회: 수정일 기준 기간 조회
    @Override
    public List<ScheduleResponseDto> findSchedulesByUpdatedDate(String startDate, String endDate) {
        return scheduleRepository.findSchedulesByUpdatedDate(startDate, endDate);
    }

    // 일정 수정
    @Transactional
    @Override
    public void updateSchedule(ScheduleUpdateRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(dto.getId());
        if(schedule == null){
            throw new IllegalArgumentException("존재하지 않는 일정");
        }
        if (!schedule.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
        int updatedCount = scheduleRepository.updateScheduleTask(dto.getId(), dto.getUpdateTask());
        if (updatedCount <= 0) {
            throw new IllegalArgumentException("일정 수정 불가");
        }
        int userUpdatedCount = userRepository.updateUserName(schedule.getUserId(), dto.getUpdateUserName());
        if (userUpdatedCount <= 0) {
            throw new IllegalArgumentException("사용자 이름 수정 불가");
        }
    }

    // 일정 삭제
    @Transactional
    @Override
    public void deleteSchedule(int scheduleId, String password) {
        Schedule schedule = scheduleRepository.findById(scheduleId);
        if(schedule == null){
            throw new IllegalArgumentException("존재하지 않는 일정");
        }
        if(!schedule.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호 불일치");
        }
        scheduleRepository.deleteSchedule(scheduleId, password);
    }
}

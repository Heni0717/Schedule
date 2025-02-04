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
import java.util.Map;

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
    public List<ScheduleResponseDto> findSchedulesByUpdatedDate(String startDate, String endDate) {
        return scheduleRepository.findSchedulesByUpdatedDate(startDate, endDate);
    }

    // 일정 수정
    @Override
    public void updateSchedule(ScheduleUpdateRequestDto dto) {
        Map<String, Object> credentials = scheduleRepository.checkPassword(dto.getId());
        String storedPassword = (String) credentials.get("password");
        int userId = ((Number) credentials.get("user_id")).intValue();

        if (!storedPassword.equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        int updatedCount = scheduleRepository.updateScheduleTask(dto.getId(), dto.getUpdateTask());
        if (updatedCount <= 0) {
            throw new IllegalArgumentException("일정 수정 불가");
        }

        int userUpdatedCount = userRepository.updateUserName(userId, dto.getUpdateUserName());
        if (userUpdatedCount <= 0) {
            throw new IllegalArgumentException("사용자 이름 수정 불가");
        }
    }

    // 일정 삭제
    @Override
    public void deleteSchedule(int scheduleId, String password) {
        int deletedCount = scheduleRepository.deleteSchedule(scheduleId, password);
        if (deletedCount <= 0) {
            throw new IllegalArgumentException("비밀번호 불일치 or 존재하지 않는 일정");
        }
    }
}

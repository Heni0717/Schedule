package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.ScheduleUpdateRequestDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 생성
    @PostMapping
    public ResponseEntity<String> createSchedule(@RequestBody ScheduleRequestDto dto) {
        try {
            scheduleService.createSchedule(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("일정 생성 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("일정 생성 실패");
        }
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules() {
        try {
            List<ScheduleResponseDto> schedules = scheduleService.findAllSchedules();
            return ResponseEntity.ok(schedules);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 단건 조회: id
    @GetMapping("/{id}")
    public ResponseEntity<?> findScheduleById(@PathVariable int id) {
        try {
            ScheduleResponseDto dto = scheduleService.findScheduleById(id);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("일정 조회 실패: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일정 조회 실패");
        }
    }

    // 다건 조회: 작성자 GET /schedules/users/name/{userName}
    @GetMapping("/users/name/{userName}")
    public ResponseEntity<?> findSchedulesByUserName(@PathVariable String userName) {
        try {
            List<ScheduleResponseDto> dtos = scheduleService.findSchedulesByUserName(userName);
            return ResponseEntity.ok(dtos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("일정 조회 실패: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일정 조회 실패");
        }
    }

    // 다건 조회: 수정일 GET /schedules/updated/{updatedDate}
    @GetMapping("/updated")
    public ResponseEntity<?> findSchedulesByUpdatedDate(@RequestParam String startDate, @RequestParam String endDate) {
        try {
            List<ScheduleResponseDto> dtos = scheduleService.findSchedulesByUpdatedDate(startDate, endDate);
            return ResponseEntity.ok(dtos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("일정 조회 실패: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일정 조회 실패");
        }
    }

    // 수정
    @PutMapping("/update")
    public ResponseEntity<?> updateSchedule(@RequestBody ScheduleUpdateRequestDto dto){
        try {
            scheduleService.updateSchedule(dto);
            return ResponseEntity.ok("수정 완료");
        } catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("수정 실패: " + e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정 실패");
        }
    }

    // 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable int scheduleId, @RequestBody Map<String, String> body) {
        String password = body.get("password");
        try {
            scheduleService.deleteSchedule(scheduleId, password);
            return ResponseEntity.ok("삭제 성공");
        } catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("삭제 실패: " + e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
        }
    }

}

package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    // 전체 일정 조회
//    @GetMapping
//    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(){
//        return new ResponseEntity<>(scheduleService.findAllSchedules(), HttpStatus.OK);
//    }
//
//    // 일정 조회(id값)
//    @GetMapping("/{id}")
//    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){
//        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
//    }

}

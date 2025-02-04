package com.example.schedule.controller;

import com.example.schedule.dto.UserInfoRequestDto;
import com.example.schedule.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // User 생성
    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserInfoRequestDto dto){
        try {
            userService.createUser(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("사용자 등록 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용자 등록 실패");
        }
    }
}

package com.example.schedule.service;

import com.example.schedule.dto.UserInfoRequestDto;
import com.example.schedule.entity.UserInfo;
import com.example.schedule.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // User 생성
    @Transactional
    @Override
    public void createUser(UserInfoRequestDto dto) {
        UserInfo newUser = UserInfo.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
        userRepository.createUserInfo(newUser);
    }
}

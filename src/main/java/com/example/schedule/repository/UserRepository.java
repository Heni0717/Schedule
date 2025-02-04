package com.example.schedule.repository;

import com.example.schedule.entity.UserInfo;

public interface UserRepository {

    int createUserInfo(UserInfo userinfo);
    int updateUserName(Long id, String updateName);

}

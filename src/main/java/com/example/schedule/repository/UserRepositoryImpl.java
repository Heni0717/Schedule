package com.example.schedule.repository;

import com.example.schedule.entity.UserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createUserInfo(UserInfo userinfo) {

        String sql = "insert into userInfo (name, email, created_at, updated_at)" +
                " values (?, ?, current_timestamp, current_timestamp)";
        return jdbcTemplate.update(sql, userinfo.getName(), userinfo.getEmail());
    }
}

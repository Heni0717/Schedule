package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 일정 생성
    @Override
    public int createSchedule(Schedule schedule) {
        String sql = "INSERT INTO schedule (task, password, user_id, created_at, updated_at)" +
                " VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
        return jdbcTemplate.update(sql, schedule.getTask(), schedule.getPassword(), schedule.getUserId());
    }

    // RowMapper(일정 조회 공통 포맷?)
    private final RowMapper<ScheduleResponseDto> scheduleRowMapper = new RowMapper<ScheduleResponseDto>() {
        @Override
        public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String task = rs.getString("task");
            Timestamp createdAt = rs.getTimestamp("created_at");
            Timestamp updatedAt = rs.getTimestamp("updated_at");
            int userId = rs.getInt("user_id");
            String userName = rs.getString("user_name");
            return new ScheduleResponseDto(id, task, createdAt.toLocalDateTime(), updatedAt.toLocalDateTime(), userId, userName);
        }
    };


    // 전체 일정 조회
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        String sql = "SELECT s.*, u.name user_name " +
                "FROM schedule s JOIN userInfo u ON s.user_id = u.id";
        return jdbcTemplate.query(sql, scheduleRowMapper);
    }

    // 단건 조회 (일정 식별자-id)
    @Override
    public ScheduleResponseDto findScheduleById(int id) {
        String sql = "SELECT s.*, u.name user_name " +
                "FROM schedule s JOIN userInfo u ON s.user_id = u.id WHERE s.id = ?";
        try{
            return jdbcTemplate.queryForObject(sql, scheduleRowMapper, id);
        }catch (EmptyResultDataAccessException E){
            return null;
        }
    }

    // 다건 조회 (작성자)
    @Override
    public List<ScheduleResponseDto> findSchedulesByUserName(String userName) {
        String sql = "SELECT s.*, u.name user_name " +
                "FROM schedule s JOIN userInfo u ON s.user_id = u.id WHERE u.name = ?";
        return jdbcTemplate.query(sql, scheduleRowMapper, userName);
    }

    // 다건 조회 (수정일)
    @Override
    public List<ScheduleResponseDto> findSchedulesByUpdatedDate(String updatedAt) {
        String sql = "SELECT s.*, u.name as user_name " +
                "FROM schedule s JOIN userInfo u ON s.user_id = u.id WHERE DATE(s.updated_at) = ?";
        return jdbcTemplate.query(sql, scheduleRowMapper, updatedAt);
    }


}

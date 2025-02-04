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

    // 일정 조회용 공통 RowMapper
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

    // 단건 조회: id
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

    // 다건 조회: 작성자
    @Override
    public List<ScheduleResponseDto> findSchedulesByUserName(String userName) {
        String sql = "SELECT s.*, u.name user_name " +
                "FROM schedule s JOIN userInfo u ON s.user_id = u.id WHERE u.name = ?";
        return jdbcTemplate.query(sql, scheduleRowMapper, userName);
    }

    // 다건 조회: 수정일 기준 기간 조회
    @Override
    public List<ScheduleResponseDto> findSchedulesByUpdatedDate(String startDate, String endDate) {
        String sql = "SELECT s.*, u.name as user_name " +
                "FROM schedule s JOIN userInfo u ON s.user_id = u.id WHERE DATE(s.updated_at) BETWEEN ? AND ? ORDER BY s.updated_at DESC";
        return jdbcTemplate.query(sql, scheduleRowMapper, startDate, endDate);
    }

    // 일정 수정: task
    @Override
    public int updateScheduleTask(int id, String updateTask) {
        String sql = "UPDATE schedule SET task = ?, updated_at = current_timestamp WHERE id = ?";
        return jdbcTemplate.update(sql, updateTask, id);
    }

    // 일정 삭제
    @Override
    public int deleteSchedule(int id, String password) {
        String sql = "DELETE FROM schedule WHERE id = ? AND password = ?";
        return jdbcTemplate.update(sql, id, password);
    }

    // 일정 조회(검증용, 반환x)
    @Override
    public Schedule findById(int id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.query(sql, ps -> {
            ps.setInt(1, id);
        }, rs -> {
            if (rs.next()) {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("task"),
                        rs.getString("password"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime(),
                        rs.getLong("user_id")
                );
            }
            return null;
        });
    }
}

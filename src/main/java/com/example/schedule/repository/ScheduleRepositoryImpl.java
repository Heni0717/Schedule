package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 일정 생성
    @Override
    public int createSchedule(Schedule schedule) {
        String sql = "insert into schedule (task, password, user_id, created_at, updated_at)" +
                " values (?, ?, ?, current_timestamp, current_timestamp)";
        return jdbcTemplate.update(sql, schedule.getTask(), schedule.getPassword(), schedule.getUserId());
    }

    // 전체 일정 조회
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        String sql = "select s.*, u.name user_name from schedule s join userInfo u on s.user_id = u.id";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<ScheduleResponseDto> dtos = new ArrayList<>();
        for(Map<String, Object> row:rows){
            int id = ((Number) row.get("id")).intValue();
            String task = (String) row.get("task");
            Timestamp createdAt = (Timestamp) row.get("created_at");
            Timestamp updatedAt = (Timestamp) row.get("updated_at");
            int userId = ((Number) row.get("user_id")).intValue();
            String userName = (String) row.get("user_name");
            dtos.add(new ScheduleResponseDto(id, task, createdAt.toLocalDateTime(), updatedAt.toLocalDateTime(), userId, userName));
        }
        return dtos;
    }

//    // 일정 조회(id값)
//    @Override
//    public Optional<Schedule> findScheduleById(Long id) {
//        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapper2(), id);
//        return result.stream().findAny();
//    }
//
//    @Override
//    public Schedule findScheduleByIdOrElseThrow(Long id) {
//        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapper2(), id);
//        return result.stream().findAny().orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id =" + id));
//    }



//
//    private RowMapper<Schedule> scheduleRowMapper2(){
//        return new RowMapper<Schedule>() {
//            @Override
//            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return new Schedule(
//                        rs.getLong("id"),
//                        rs.getString("author"),
//                        null,
//                        rs.getString("task"),
//                        rs.getTimestamp("created_at").toLocalDateTime(),
//                        rs.getTimestamp("updated_at").toLocalDateTime()
//                );
//            }
//        };
//    }
}

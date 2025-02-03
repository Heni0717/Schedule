package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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
//
//    // 전체 일정 조회
//    @Override
//    public List<ScheduleResponseDto> findAllSchedules() {
//        return jdbcTemplate.query("select * from schedule", scheduleRowMapper());
//    }
//
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
//
//
//
//
//
//    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
//        return new RowMapper<ScheduleResponseDto>() {
//            @Override
//            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return new ScheduleResponseDto(
//                        rs.getLong("id"),
//                        rs.getString("author"),
//                        rs.getString("task"),
//                        rs.getTimestamp("created_at").toLocalDateTime(),
//                        rs.getTimestamp("updated_at").toLocalDateTime()
//                );
//            }
//        };
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

package me.restapi.dao.Imp;

import me.restapi.dao.TaskDao;
import me.restapi.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskDaoImp implements TaskDao {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbc;

    @Override
    public UUID createTask(Task task) {
        String query = "INSERT INTO task (status, date) " +
                "VALUES (:status, :date)";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("status", task.getStatus())
                .addValue("date", task.getTimestamp());

        namedJdbc.update(query, parameterSource, holder);

        return (UUID) holder.getKeyList().get(0).get("id");
    }

    @Override
    public Task getTass(UUID id) {
        String query = "SELECT status, date FROM task WHERE id = :id";

        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);

        return (Task) namedJdbc.queryForObject(query, parameterSource, new TaskMapper());
    }

}

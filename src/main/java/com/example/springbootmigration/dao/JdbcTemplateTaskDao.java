package com.example.springbootmigration.dao;


import com.example.springbootmigration.model.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Profile("jdbctemplate")
public class JdbcTemplateTaskDao implements TaskDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateTaskDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public void create(String title) {
        jdbcTemplate.update("INSERT INTO tasks (title) VALUES (?)", title);
    }

    @Override
    public List<Task> findAll() {
        return jdbcTemplate.query("SELECT * FROM tasks",
                (rs, row) -> new Task(rs.getLong("id"), rs.getString("title")));
    }
}
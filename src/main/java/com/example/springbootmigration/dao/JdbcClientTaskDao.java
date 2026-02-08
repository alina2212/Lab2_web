package com.example.springbootmigration.dao;

import com.example.springbootmigration.model.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Profile("jdbcclient") // Профиль для экстра-задания
public class JdbcClientTaskDao implements TaskDao {

    private final JdbcClient jdbcClient;

    // Spring автоматически внедрит JdbcClient
    public JdbcClientTaskDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void create(String title) {
        jdbcClient.sql("INSERT INTO tasks (title) VALUES (?)")
                .param(title)
                .update();
    }

    @Override
    public List<Task> findAll() {
        return jdbcClient.sql("SELECT * FROM tasks")
                .query(Task.class) // Автоматически мапит строки в объект Task
                .list();
    }
}
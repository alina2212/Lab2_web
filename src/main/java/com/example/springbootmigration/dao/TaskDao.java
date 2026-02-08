package com.example.springbootmigration.dao;

import com.example.springbootmigration.model.Task;

import java.util.List;

public interface TaskDao {
    void create(String title);
    List<Task> findAll();
}

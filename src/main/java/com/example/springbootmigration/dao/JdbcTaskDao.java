package com.example.springbootmigration.dao;

import com.example.springbootmigration.model.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("jdbc")
public class JdbcTaskDao implements TaskDao {
    private final DataSource dataSource;

    public JdbcTaskDao(DataSource dataSource) { this.dataSource = dataSource; }

    @Override
    public void create(String title) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO tasks (title) VALUES (?)")) {
            ps.setString(1, title);
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM tasks")) {
            while (rs.next()) {
                tasks.add(new Task(rs.getLong("id"), rs.getString("title")));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return tasks;
    }
}

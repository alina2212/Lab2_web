package com.example.springbootmigration;


import com.example.springbootmigration.dao.TaskDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MigrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MigrationApplication.class, args);
    }

    @Bean
    CommandLineRunner start(TaskDao taskDao) {
        return args -> {
            System.out.println("--- Тестирование DAO ---");
            taskDao.create("Купить шоколад Алёнка");
            taskDao.findAll().forEach(System.out::println);
            System.out.println("Используется профиль: " + taskDao.getClass().getSimpleName());
        };
    }
}
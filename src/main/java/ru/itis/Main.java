package ru.itis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        Properties properties = new Properties();

        try {
            properties.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.user"));
        config.setPassword(properties.getProperty("db.password"));
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.pool-size")));

        DataSource dataSource = new HikariDataSource(config);
        CoursesRepositoryJdbcTemplateImpl coursesRepositoryJdbcTemplate = new CoursesRepositoryJdbcTemplateImpl(dataSource);
        LessonsRepositoryJdbcTemplateImpl lessonsRepositoryJdbcTemplate = new LessonsRepositoryJdbcTemplateImpl(dataSource);
        lessonsRepositoryJdbcTemplate.findAll().forEach(System.out::println);
        System.out.println("Everything is OK");
    }
}

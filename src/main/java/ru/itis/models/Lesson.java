package ru.itis.models;

public class Lesson {
    private Integer id;
    private String name;
    private String day_of_week;
    private String time;
    private Course course;

    public Lesson(Integer id, String name, String day_of_week, String time, Course course) {
        this.id = id;
        this.name = name;
        this.day_of_week = day_of_week;
        this.time = time;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", day_of_week='" + day_of_week + '\'' +
                ", time='" + time + '\'' +
                ", course=" + course.getName() +
                '}';
    }
}

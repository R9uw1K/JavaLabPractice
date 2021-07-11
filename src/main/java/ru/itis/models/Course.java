package ru.itis.models;

import java.util.List;

public class Course {
    private Integer id;
    private String name;
    private String beginning;
    private String ending;
    private Integer teacher_id;
    private List<Student> students;

    public Course(Integer id, String name, String beginning, String ending, Integer teacher_id, List<Student> students) {
        this.id = id;
        this.name = name;
        this.beginning = beginning;
        this.ending = ending;
        this.teacher_id = teacher_id;
        this.students = students;
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

    public String getBeginning() {
        return beginning;
    }

    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents_id(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beginning='" + beginning + '\'' +
                ", ending='" + ending + '\'' +
                ", teacher_id=" + teacher_id +
                ", student_id=" + students +
                '}';
    }
}

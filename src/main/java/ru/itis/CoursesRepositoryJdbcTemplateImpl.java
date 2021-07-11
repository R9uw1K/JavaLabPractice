package ru.itis;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.models.Course;
import ru.itis.models.Student;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoursesRepositoryJdbcTemplateImpl implements CoursesRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from (select c.id as course_id, * from course as c left join course_student as b on c.id = b.course_id order by c.id) as d left join student on d.student_id = student.id";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " where d.id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_BY_COURSE_NAME = SQL_SELECT_ALL + " where d.name = ?";

    //language=SQL
    private static final String SQL_INSERT_COURSE = "insert into course(name, beginning, ending, teacher_id) values (?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_INSERT_STUDENT_INTO_COURSE_STUDENT = "insert into course_student(course_id, student_id) values (?, ?)";

    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update course set name = ?, beginning = ?, ending = ?, teacher_id = ? where id = ?";

    //language=SQL
    private static final String SQL_DELETE_STUDENTS_FROM_COURSE_BY_COURSE_ID = "delete from course_student where course_id = ?";

    //language=SQL
    private static final String SQL_DELETE_COURSE_BY_COURSE_ID = "delete from course where id = ?";

    private final JdbcTemplate jdbcTemplate;

    public CoursesRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final ResultSetExtractor<List<Course>> coursesResultSetExtractor = rows -> {
        List<Course> courses = new ArrayList<>();

        while (rows.next()) {

            Integer course_id = rows.getInt("course_id");
            String course_name = rows.getString("name");
            String course_beginning = rows.getString("beginning");
            String course_ending = rows.getString("ending");
            Integer teacher_id = rows.getInt("teacher_id");

            Course course = new Course(course_id, course_name, course_beginning, course_ending, teacher_id, new ArrayList<Student>());

            do {
                Integer student_id = rows.getInt("student_id");
                String firstName = rows.getString("first_name");
                String lastName = rows.getString("last_name");
                Integer group_id = rows.getInt("group_id");
                Student student = new Student(student_id, firstName, lastName, group_id, null);
                course.getStudents().add(student);
            } while (rows.next() && course_id == rows.getInt("course_id"));

            courses.add(course);
        }
        return courses;
    };

    @Override
    public List<Course> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, coursesResultSetExtractor);
    }

    @Override
    public Optional<Course> findById(Integer id) {
        List<Course> courses = jdbcTemplate.query(SQL_SELECT_BY_ID, coursesResultSetExtractor, id);
        if (courses.size() == 1) {
            return Optional.of(courses.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Course> findAllByName(String name) {
        return jdbcTemplate.query(SQL_SELECT_ALL_BY_COURSE_NAME, coursesResultSetExtractor, name);
    }

    @Override
    public void save(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_COURSE, new String[]{"id"});

            statement.setString(1, course.getName());
            statement.setString(2, course.getBeginning());
            statement.setString(3, course.getEnding());
            statement.setInt(4, course.getTeacher_id());

            return statement;
        }, keyHolder);

        course.setId(keyHolder.getKey().intValue());

        for (Student student : course.getStudents()) {
            jdbcTemplate.update(SQL_INSERT_STUDENT_INTO_COURSE_STUDENT, course.getId(), student.getId());
        }
    }

    @Override
    public void delete(Course course) {
        jdbcTemplate.update(SQL_DELETE_STUDENTS_FROM_COURSE_BY_COURSE_ID, course.getId());

        jdbcTemplate.update(SQL_DELETE_COURSE_BY_COURSE_ID, course.getId());
    }

    @Override
    public void update(Course course) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, course.getName(), course.getBeginning(), course.getEnding(), course.getTeacher_id(), course.getId());

        jdbcTemplate.update(SQL_DELETE_STUDENTS_FROM_COURSE_BY_COURSE_ID, course.getId());

        for (Student student : course.getStudents()) {
            jdbcTemplate.update(SQL_INSERT_STUDENT_INTO_COURSE_STUDENT, course.getId(), student.getId());
        }
    }

}

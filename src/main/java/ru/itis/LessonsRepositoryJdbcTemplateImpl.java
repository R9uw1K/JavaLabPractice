package ru.itis;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.models.Course;
import ru.itis.models.Lesson;
import ru.itis.models.Student;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LessonsRepositoryJdbcTemplateImpl implements LessonsRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select lesson.id as lesson_id, lesson.name as lesson_name, * from ((select c.id as course_id, * from course as c left join course_student as b on c.id = b.course_id order by c.id) as d left join student on d.student_id = student.id) inner join lesson on lesson.course_id = d.id";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " where lesson.id = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL_BY_LESSON_NAME = SQL_SELECT_ALL + " where lesson.name = ?";

    //language=SQL
    private static final String SQL_INSERT_STUDENT_INTO_COURSE_STUDENT = "insert into course_student(course_id, student_id) values (?, ?)";

    //language=SQL
    private static final String SQL_INSERT_LESSON = "insert into lesson(name, day_of_week, time, course_id) values (?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update lesson set name = ?, day_of_week = ?, time = ? where id = ?";

   //language=SQL
    private static final String SQL_UPDATE_COURSE_BY_ID = "update course set name = ?, beginning = ?, ending = ?, teacher_id = ? where id = ?";

    //language=SQL
    private static final String SQL_DELETE_STUDENTS_FROM_COURSE_BY_COURSE_ID = "delete from course_student where course_id = ?";

    //language=SQL
    private static final String SQL_DELETE_LESSON_BY_ID = "delete from lesson where id = ?";

    //language=SQL
    private static final String SQL_DELETE_COURSE_BY_COURSE_ID = "delete from course where id = ?";

    private final JdbcTemplate jdbcTemplate;

    public LessonsRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final ResultSetExtractor<List<Lesson>> lessonsResultSetExtractor = rows -> {
        List<Lesson> lessons = new ArrayList<>();

        while (rows.next()) {

            Integer lesson_id = rows.getInt("lesson_id");
            String lesson_name = rows.getString("lesson_name");
            String lesson_day = rows.getString("day_of_week");
            String lesson_time = rows.getString("time");

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
            } while (rows.next() && lesson_id == rows.getInt("lesson_id"));

            lessons.add(new Lesson(lesson_id, lesson_name, lesson_day, lesson_time, course));
        }
        return lessons;
    };

    @Override
    public List<Lesson> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, lessonsResultSetExtractor);
    }

    @Override
    public Optional<Lesson> findById(Integer id) {
        List<Lesson> lessons = jdbcTemplate.query(SQL_SELECT_BY_ID, lessonsResultSetExtractor, id);
        if (lessons.size() == 1) {
            return Optional.of(lessons.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Lesson> findAllByName(String name) {
        return jdbcTemplate.query(SQL_SELECT_ALL_BY_LESSON_NAME, lessonsResultSetExtractor, name);
    }

    @Override
    public void save(Lesson lesson) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_LESSON, new String[]{"id"});

            statement.setString(1, lesson.getName());
            statement.setString(2, lesson.getDay_of_week());
            statement.setString(3, lesson.getTime());
            statement.setInt(4, lesson.getCourse().getId());

            return statement;
        }, keyHolder);

        lesson.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void delete(Lesson lesson) {
        jdbcTemplate.update(SQL_DELETE_LESSON_BY_ID, lesson.getId());

        jdbcTemplate.update(SQL_DELETE_STUDENTS_FROM_COURSE_BY_COURSE_ID, lesson.getCourse().getId());

        jdbcTemplate.update(SQL_DELETE_COURSE_BY_COURSE_ID, lesson.getCourse().getId());
    }

    @Override
    public void update(Lesson lesson) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, lesson.getName(), lesson.getDay_of_week(), lesson.getTime(), lesson.getId());

        jdbcTemplate.update(SQL_UPDATE_COURSE_BY_ID, lesson.getCourse().getName(), lesson.getCourse().getBeginning(), lesson.getCourse().getEnding(), lesson.getCourse().getTeacher_id(), lesson.getCourse().getId());

        jdbcTemplate.update(SQL_DELETE_STUDENTS_FROM_COURSE_BY_COURSE_ID, lesson.getCourse().getId());

        for (Student student : lesson.getCourse().getStudents()) {
            jdbcTemplate.update(SQL_INSERT_STUDENT_INTO_COURSE_STUDENT, lesson.getCourse().getId(), student.getId());
        }
    }
}

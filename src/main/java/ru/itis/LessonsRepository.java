package ru.itis;

import ru.itis.models.Lesson;
import java.util.List;
import java.util.Optional;

public interface LessonsRepository {

    List<Lesson> findAll();
    Optional<Lesson> findById(Integer id);
    List<Lesson> findAllByName(String name);

    void save (Lesson lesson);
    void delete(Lesson lesson);
    void update (Lesson lesson);

}

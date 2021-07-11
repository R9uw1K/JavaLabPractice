insert into teacher(first_name, last_name, experience)
values ('Марсель', 'Сидиков', 8);
insert into teacher(first_name, last_name, experience)
values ('Яна', 'Шестакова', 7);
insert into teacher(first_name, last_name, experience)
values ('Анастасия', 'Белова', 20);
insert into teacher(first_name, last_name, experience)
values ('Артема', 'Михеев', 30);
insert into teacher(first_name, last_name, experience)
values ('Даниэль', 'Сорокин', 2);
insert into teacher(first_name, last_name, experience)
values ('Мария', 'Киселева', 1);

insert into student(first_name, last_name, group_id)
values ('Полина', 'Соколова', 2);
insert into student(first_name, last_name, group_id)
values ('Федор', 'Софронов', 2);
insert into student(first_name, last_name, group_id)
values ('Ника', 'Воробьева', 2);
insert into student(first_name, last_name, group_id)
values ('Гордей', 'Дегтярев', 2);
insert into student(first_name, last_name, group_id)
values ('Максим', 'Орлов', 2);
insert into student(first_name, last_name, group_id)
values ('Елизавета', 'Мельникова', 2);
insert into student(first_name, last_name, group_id)
values ('Ксения', 'Сахарова', 5);
insert into student(first_name, last_name, group_id)
values ('Марина', 'Демина', 5);
insert into student(first_name, last_name, group_id)
values ('Максим', 'Большаков', 5);
insert into student(first_name, last_name, group_id)
values ('Милана', 'Васильева', 5);
insert into student(first_name, last_name, group_id)
values ('Виктория', 'Вишневская', 5);
insert into student(first_name, last_name, group_id)
values ('Екатерина', 'Лосева', 1);
insert into student(first_name, last_name, group_id)
values ('Вера', 'Федорова', 1);
insert into student(first_name, last_name, group_id)
values ('Елисей', 'Кузнецов', 1);
insert into student(first_name, last_name, group_id)
values ('Тимофей', 'Крылов', 1);

insert into course(name, beginning, ending, teacher_id)
values ('Русский язык', '2022-09-02', '2023-06-1', 2);
insert into course(name, beginning, ending, teacher_id)
values ('Алгебра', '2022-09-03', '2023-06-1', 3);
insert into course(name, beginning, ending, teacher_id)
values ('Информатика', '2022-09-02', '2023-06-1', 1);
insert into course(name, beginning, ending, teacher_id)
values ('Геометрия', '2022-09-04', '2023-06-1', 4);
insert into course(name, beginning, ending, teacher_id)
values ('Физическая культура', '2022-09-03', '2023-06-1', 5);

insert into lesson(name, day_of_week, time, course_id)
values ('Русский язык-1', 'Понедельник', '8:00-9:30', 1);
insert into lesson(name, day_of_week, time, course_id)
values ('Русский язык-2', 'Среда', '10:00-11:30', 1);
insert into lesson(name, day_of_week, time, course_id)
values ('Русский язык-3', 'Пятница', '10:00-11:30', 1);
insert into lesson(name, day_of_week, time, course_id)
values ('Алгебра', 'Суббота', '11:00-12:30', 2);
insert into lesson(name, day_of_week, time, course_id)
values ('Информатика-1', 'Вторник', '14:00-15:30', 2);
insert into lesson(name, day_of_week, time, course_id)
values ('Информатика-2)', 'Четверг', '14:00-15:30', 3);
insert into lesson(name, day_of_week, time, course_id)
values ('Информатика-3', 'Вторник', '10:00-11:30', 3);
insert into lesson(name, day_of_week, time, course_id)
values ('Физическая культура-1', 'Понедельник', '10:00-11:30', 4);
insert into lesson(name, day_of_week, time, course_id)
values ('Физическая культура-2', 'Среда', '08:30-10:00', 4);
insert into lesson(name, day_of_week, time, course_id)
values ('Геометрия-1', 'Среда', '10:00-11:30', 5);
insert into lesson(name, day_of_week, time, course_id)
values ('Геометрия-2', 'Среда', '12:00-13:30', 5);

insert into course_student(course_id, student_id)
values (1, 1);
insert into course_student(course_id, student_id)
values (1, 2);
insert into course_student(course_id, student_id)
values (1, 3);
insert into course_student(course_id, student_id)
values (1, 4);
insert into course_student(course_id, student_id)
values (1, 5);
insert into course_student(course_id, student_id)
values (2, 6);
insert into course_student(course_id, student_id)
values (2, 7);
insert into course_student(course_id, student_id)
values (2, 8);
insert into course_student(course_id, student_id)
values (2, 9);
insert into course_student(course_id, student_id)
values (2, 10);
insert into course_student(course_id, student_id)
values (3, 11);
insert into course_student(course_id, student_id)
values (3, 12);
insert into course_student(course_id, student_id)
values (3, 13);
insert into course_student(course_id, student_id)
values (4, 14);
insert into course_student(course_id, student_id)
values (4, 15);
insert into course_student(course_id, student_id)
values (3, 15);
insert into course_student(course_id, student_id)
values (3, 8);
insert into course_student(course_id, student_id)
values (3, 10);
insert into course_student(course_id, student_id)
values (3, 12);
insert into course_student(course_id, student_id)
values (4, 1);
insert into course_student(course_id, student_id)
values (4, 5);
insert into course_student(course_id, student_id)
values (4, 8);
insert into course_student(course_id, student_id)
values (4, 9);
insert into course_student(course_id, student_id)
values (4, 15);
insert into course_student(course_id, student_id)
values (5, 1);
insert into course_student(course_id, student_id)
values (5, 9);
insert into course_student(course_id, student_id)
values (5, 15);

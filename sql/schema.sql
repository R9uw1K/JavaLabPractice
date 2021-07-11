create table teacher
(
    id         serial primary key,
    first_name varchar(30) not null,
    last_name  varchar(30) not null,
    experience integer     not null
);

create table course
(
    id         serial primary key,
    name       varchar(30) not null,
    beginning  varchar(10) not null,
    ending     varchar(10) not null,
    teacher_id integer,
    foreign key (teacher_id) references teacher (id)
);

create table lesson
(
    id          serial primary key,
    name        varchar(35) not null,
    day_of_week varchar(20) not null,
    time        varchar(11) not null,
    course_id   integer,
    foreign key (course_id) references course (id)
);

create table student
(
    id         serial primary key,
    first_name varchar(20) not null,
    last_name  varchar(20) not null,
    group_id   integer     not null
);

create table course_student
(
    course_id  integer,
    student_id integer,
    foreign key (course_id) references course (id),
    foreign key (student_id) references student (id)
);
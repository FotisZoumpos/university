CREATE TABLE IF NOT EXISTS course_student
(
    student_id bigint not null,
    course_id  bigint not null,
    constraint FKacc7gn1l63go6x8dsx0wdnr38
        foreign key (student_id) references student (id),
    constraint FKlmsbddqkv96q4nijkrxuof3ug
        foreign key (course_id) references course (id)
);

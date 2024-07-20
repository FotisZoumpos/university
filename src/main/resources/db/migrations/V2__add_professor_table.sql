CREATE TABLE IF NOT EXISTS course
(
    id           bigint auto_increment
        primary key,
    description  varchar(255) not null,
    name         varchar(255) not null,
    professor_id bigint       null,
    constraint UK5o6x4fpafbywj4v2g0owhh11r
        unique (name),
    constraint FKsj4okul9jc8m3p4tsnuobqjpb
        foreign key (professor_id) references professor (id)
);

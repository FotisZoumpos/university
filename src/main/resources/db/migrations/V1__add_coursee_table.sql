CREATE TABLE IF NOT EXISTS course
(
    id           bigint auto_increment
        primary key,
    description  varchar(255) not null,
    name         varchar(255) not null,
    professor_id bigint       null,
    constraint UK4xqvdpkafb91tt3hsb67ga3fj
        unique (name),
    constraint FKqctak3o6xmul2nu2561al3pb5
        foreign key (professor_id) references professor (id)
);


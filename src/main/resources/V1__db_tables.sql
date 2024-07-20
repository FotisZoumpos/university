create table course
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

create table professor
(
    id        bigint auto_increment
        primary key,
    birthday  date         not null,
    email     varchar(255) not null,
    firstname varchar(255) not null,
    gender    tinyint      not null,
    lastname  varchar(255) not null,
    phone     varchar(255) not null,
    constraint UK7eo9f81hj74qjpuye6jfbaw2v
        unique (phone),
    constraint UKqjm28ojevoom770jyieljec3e
        unique (email),
    constraint chk_email_format
        check (regexp_like(`email`, _utf8mb4'^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Z|a-z]{2,}$')),
	check (`gender` between 0 and 2)
);

create table student
(
    id        bigint auto_increment
        primary key,
    birthday  date         not null,
    email     varchar(255) not null,
    firstname varchar(255) not null,
    gender    tinyint      not null,
    lastname  varchar(255) not null,
    phone     varchar(255) not null,
    constraint UK5s5e6lj1siq6ef1tm7p2g4uul
        unique (phone),
    constraint UKfe0i52si7ybu0wjedj6motiim
        unique (email),
    constraint chk_email_format_v2
        check (regexp_like(`email`, _utf8mb4'^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Z|a-z]{2,}$')),
	constraint chk_gender
		check (`gender` between 0 and 2)
);

create table course_student
(
    student_id bigint not null,
    course_id  bigint not null,
    constraint FK4xxxkt1m6afc9vxp3ryb0xfhi
        foreign key (student_id) references student (id),
    constraint FKlmj50qx9k98b7li5li74nnylb
        foreign key (course_id) references course (id)
);
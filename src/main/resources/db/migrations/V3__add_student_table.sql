CREATE TABLE IF NOT EXISTS student
(
    id        bigint auto_increment
        primary key,
    birthday  date         not null,
    email     varchar(255) not null,
    firstname varchar(255) not null,
    gender    tinyint      not null,
    lastname  varchar(255) not null,
    phone     varchar(255) not null,
    constraint UK4j48kma5fa3dcya13gd0l3gi
        unique (phone),
    constraint UKe2rndfrsx22acpq2ty1caeuyw
        unique (email),
    check (`gender` between 0 and 2)
);

create table user (
    ID int primary key,
    ID_TOKEN text,
    ACCESS_TOKEN text,
    REFRESH_TOKEN text
) charset=utf8;

create table toi (
    ID int primary key,
    USER_ID int,
    BOOK_ID int,
    PICTURE_URL varchar(255),
    TITLE varchar(255),
    DETAIL varchar(255),
    CONTENT varchar(255),
    PUBLIC_FLG varchar(1),
    INSERT_DATE date,
    UPDATED_DATE date,
    DELETED_DATE date
) charset=utf8;

create table question (
    ID int primary key,
    TOI_ID int,
    TITLE varchar(255),
    DETAIL varchar(255),
    REQUIRED varchar(1),
    ORDER_ID int,
    INSERT_DATE date,
    UPDATED_DATE date,
    DELETED_DATE date
) charset=utf8;

create table answer (
    ID int primary key,
    ISBN varchar(13),
    PICTURE_URL varchar(255),
    TITLE varchar(255),
    DETAIL varchar(255),
    LINK_URL varchar(255)
) charset=utf8;

create table talk (
    ID int primary key,
    TOI_ID int,
    USER_ID int,
    TALK_ID int,
    CONTENT varchar(255),
    INSERT_DATE date,
    DELETED_DATE date
)
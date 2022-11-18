create table if not exists answer (
    id bigint generated by default as identity,
    answer_text varchar(255),
    question_id bigint,
    primary key (id)
);
create table if not exists question (
    id bigint generated by default as identity,
    is_multiple_choice boolean,
    question_text varchar(255),
    survey_id bigint,
    primary key (id)
);
create table if not exists survey (
    id bigint generated by default as identity,
    title varchar(255),
    author_id bigint,
    primary key (id)
);
create table if not exists survey_user (
    id bigint generated by default as identity,
    account_non_locked boolean,
    failed_attempts integer,
    lock_time timestamp,
    password varchar(255),
    username varchar(255),
    primary key (id)
);
create table if not exists user_result (
    id bigint generated by default as identity,
    answers json,
    survey_id bigint,
    user_id bigint,
    primary key (id)
);
create table if not exists user_role (
    user_id bigint not null,
    roles integer
);
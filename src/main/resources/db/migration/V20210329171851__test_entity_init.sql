CREATE TABLE test_entity (
    id bigserial primary key,
    name varchar(100) not null,
    is_deleted boolean not null default false,
    is_important boolean not null default false
);

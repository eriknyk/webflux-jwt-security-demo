CREATE DATABASE "webflux-security";

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(64),
    password   VARCHAR(64),
    roles      TEXT[],
    first_name VARCHAR(64),
    last_name  VARCHAR(64),
    enabled    BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

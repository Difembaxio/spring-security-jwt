CREATE TABLE IF NOT EXISTS users
(
    login VARCHAR
(
    255
) NOT NULL UNIQUE,
    password VARCHAR
(
    255
) NOT NULL,
    role VARCHAR
(
    255
) NOT NULL,
    PRIMARY KEY
(
    login
)
    );
CREATE DATABASE testdb;
\c testdb
CREATE TABLE IF NOT EXISTS employee
(
    ID  SERIAL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT
);
INSERT INTO employee (first_name, last_name, email) VALUES
('Leslie','Andrews','leslie@example.com'),
('Emma','Baumgarten','emma@example.com'),
('Avani','Gupta','avani@example.com'),
('Yuri','Petrov','yuri@example.com'),
('Juan','Vega','juan@example.com');
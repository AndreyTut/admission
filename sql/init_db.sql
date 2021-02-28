DROP TABLE IF EXISTS diploma CASCADE;
DROP TABLE IF EXISTS user_role CASCADE;
DROP TABLE IF EXISTS user_db CASCADE;
DROP TABLE IF EXISTS subject CASCADE;
DROP TABLE IF EXISTS faculty CASCADE;
DROP TABLE IF EXISTS faculty_subject CASCADE;
DROP TABLE IF EXISTS student_mark CASCADE;
DROP TABLE IF EXISTS student_faculty CASCADE;

CREATE TABLE faculty
(
  id         SERIAL PRIMARY KEY,
  name_en    VARCHAR,
  name_ua    VARCHAR,
  vacs       INTEGER,
  vacs_contr INTEGER,
  finalized  BOOLEAN DEFAULT FALSE
);


CREATE TABLE user_db
(
  id           SERIAL PRIMARY KEY,
  email        VARCHAR           NOT NULL,
  password     VARCHAR           NOT NULL,
  first_name   VARCHAR,
  last_name    VARCHAR,
  patronymic   VARCHAR,
  city         VARCHAR,
  region       VARCHAR,
  school_name  VARCHAR,
  diplom_image BYTEA,
  enabled      BOOL DEFAULT TRUE NOT NULL,
  diploma_id   INTEGER,
  faculty_id   INTEGER,
  status       INTEGER,
  FOREIGN KEY (faculty_id) REFERENCES faculty (id)
);
CREATE UNIQUE INDEX users_unique_email_idx ON user_db (email);

CREATE TABLE user_role
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES user_db (id) ON DELETE CASCADE
);

CREATE TABLE diploma
(
  id         SERIAL PRIMARY KEY,
  math       INTEGER,
  physics    INTEGER,
  history    INTEGER,
  literature INTEGER,
  chemistry  INTEGER,
  biology    INTEGER,
  user_id    INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user_db (id) ON DELETE CASCADE
);

ALTER TABLE user_db
  ADD CONSTRAINT diploma_fk
    FOREIGN KEY (diploma_id)
      REFERENCES diploma (id);

CREATE TABLE subject
(
  id      SERIAL PRIMARY KEY,
  name_en VARCHAR,
  name_ua VARCHAR,
  CONSTRAINT name_idx UNIQUE (name_en, name_ua)
);


CREATE TABLE faculty_subject
(
  faculty_id INTEGER,
  subject_id INTEGER,
  FOREIGN KEY (faculty_id) REFERENCES faculty (id) ON DELETE CASCADE,
  FOREIGN KEY (subject_id) REFERENCES subject (id) ON DELETE CASCADE
);

CREATE TABLE student_mark
(
  id         SERIAL PRIMARY KEY,
  user_id    INTEGER,
  subject_id INTEGER,
  mark       INTEGER,
  FOREIGN KEY (user_id) REFERENCES user_db (id) ON DELETE CASCADE,
  FOREIGN KEY (subject_id) REFERENCES subject (id) ON DELETE CASCADE
);

CREATE TABLE student_faculty
(
  student_id INTEGER,
  faculty_id INTEGER,
  FOREIGN KEY (student_id) REFERENCES user_db (id) ON DELETE CASCADE,
  FOREIGN KEY (faculty_id) REFERENCES faculty (id) ON DELETE CASCADE
);



DELETE
FROM user_role;
DELETE
FROM user_db;

INSERT INTO faculty (name_en, name_ua, vacs, vacs_contr)
VALUES ('Mathematical', 'Математичний', 3, 5),
       ('Physical', 'Фізичний', 2, 3),
       ('Biological', 'Біологічний', 5, 7),
       ('Sociological', 'Соціологічний', 3, 3);



INSERT INTO user_db (email, password, enabled)
VALUES ('admin@gmail.com', '1', true),
       ('admin@user.net', '1', true);


INSERT INTO user_db (email, password, first_name, last_name, patronymic, city, region, school_name, enabled, faculty_id)
VALUES ('user@ukr.net', '1', 'Проскурін', 'Дмитро',
        'Сергійович', 'Київ', 'Київська', 'Школа№10', true, 4),
       ('user@user.net', '1', 'Іван', 'Богданов',
        'Миколайович', 'Сміла', 'Черкаська', 'Школа№4', true, 4),
       ('user1@user.net', '1', 'Василь', 'Ситник',
        'Іванович', 'Миргород', 'Полтавська', 'Гімназія№5', true, 4),
       ('user2@user.net', '1', 'Іван', 'Борисов', 'Іванович',
        'Лубни', 'Полтавська', 'Ліцей№5', true, 4),
       ('user3@user.net', '1', 'Ганна', 'Давидова',
        'Олексіївна', 'Вінниця', 'Вінницька', 'ПТУ№2', true, 4),
       ('user4@user.net', '1', 'Іван', 'Швець', 'Дмитрович',
        'Стрий', 'Львівська', 'Ліцей№1', true, 4),
       ('user5@user.net', '1', 'Руслан', 'Черненко',
        'Дмитрович', 'Рівне', 'Рівненська', 'Школа№5', true, 4);

INSERT INTO user_db (email, password, first_name, last_name, patronymic, city, region, school_name, enabled)
VALUES ('user11@user.net', '1', 'Філіпов', 'Едуард',
        'Микитович', 'Київ', 'Київська', 'Школа№10', true),
       ('user12@user.net', '1', 'Іван', 'Харишин',
        'Сергійович', 'Дуцьк', 'Волинська', 'Школа№4', true),
       ('user13@user.net', '1', 'Пилип', 'Глібов',
        'Миколайович', 'Миргород', 'Полтавська', 'Гімназія№7', true),
       ('user14@user.net', '1', 'Петро', 'Басов',
        'Дмитрович',
        'Решитилівка', 'Полтавська', 'Ліцей№5', true),
       ('user15@user.net', '1', 'Ганна', 'Давидова',
        'Олексіївна', 'Вінниця', 'Вінницька', 'ПТУ№2', true),
       ('user16@user.net', '1', 'Іван', 'Швець', 'Дмитрович',
        'Стрий', 'Львівська', 'Ліцей№1', true),
       ('user17@user.net', '1', 'Руслан', 'Черненко',
        'Дмитрович', 'Рівне', 'Рівненська', 'Школа№5', true);

INSERT INTO diploma (math, physics, history, literature, chemistry, biology, user_id)
VALUES (12, 12, 10, 11, 12, 12, 3),
       (12, 12, 10, 11, 12, 12, 4),
       (12, 12, 10, 11, 12, 12, 5),
       (12, 12, 10, 11, 12, 12, 6),
       (12, 12, 10, 11, 12, 12, 7),
       (12, 12, 10, 11, 12, 12, 8),
       (12, 12, 10, 11, 12, 12, 9),
       (12, 12, 10, 11, 12, 12, 10),
       (12, 12, 10, 11, 12, 12, 11),
       (12, 12, 10, 11, 12, 12, 12),
       (12, 12, 10, 11, 12, 12, 13),
       (12, 12, 10, 11, 12, 12, 14),
       (12, 12, 10, 11, 12, 12, 15),
       (12, 12, 10, 11, 12, 12, 16);

UPDATE user_db SET diploma_id=1 WHERE id=3;
UPDATE user_db SET diploma_id=2 WHERE id=4;
UPDATE user_db SET diploma_id=3 WHERE id=5;
UPDATE user_db SET diploma_id=4 WHERE id=6;
UPDATE user_db SET diploma_id=5 WHERE id=7;
UPDATE user_db SET diploma_id=6 WHERE id=8;
UPDATE user_db SET diploma_id=7 WHERE id=9;
UPDATE user_db SET diploma_id=8 WHERE id=10;
UPDATE user_db SET diploma_id=9 WHERE id=11;
UPDATE user_db SET diploma_id=10 WHERE id=12;
UPDATE user_db SET diploma_id=11 WHERE id=13;
UPDATE user_db SET diploma_id=12 WHERE id=14;
UPDATE user_db SET diploma_id=13 WHERE id=15;
UPDATE user_db SET diploma_id=14 WHERE id=16;


INSERT INTO user_role (role, user_id)
VALUES ('ROLE_ADMIN', 1),
       ('ROLE_ADMIN', 2),
       ('ROLE_USER', 3),
       ('ROLE_USER', 4),
       ('ROLE_USER', 5),
       ('ROLE_USER', 6),
       ('ROLE_USER', 7),
       ('ROLE_USER', 8),
       ('ROLE_USER', 9),
       ('ROLE_USER', 10),
       ('ROLE_USER', 11),
       ('ROLE_USER', 12),
       ('ROLE_USER', 13),
       ('ROLE_USER', 14),
       ('ROLE_USER', 15),
       ('ROLE_USER', 16);

INSERT INTO subject (name_en, name_ua)
VALUES ('Mathematics', 'Математика'),
       ('Physics', 'Фізика'),
       ('Chemistry', 'Хімія'),
       ('Biology', 'Біологія'),
       ('History', 'Історія'),
       ('Literature', 'Література');



INSERT INTO faculty_subject(faculty_id, subject_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 3),
       (3, 3),
       (3, 4),
       (4, 5),
       (4, 6);

INSERT INTO student_faculty(student_id, faculty_id)
VALUES (3, 4),
       (4, 4),
       (5, 4),
       (6, 4),
       (7, 4),
       (8, 4),
       (9, 4);

INSERT INTO student_mark(user_id, subject_id, mark)
VALUES (3, 5, 100),
       (3, 6, 100),
       (4, 5, 110),
       (4, 6, 110),
       (5, 5, 120),
       (5, 6, 120),
       (6, 5, 130),
       (6, 6, 130),
       (7, 5, 140),
       (7, 6, 140),
       (8, 5, 150),
       (8, 6, 150),
       (9, 5, 160),
       (9, 6, 160);


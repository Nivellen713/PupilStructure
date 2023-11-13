CREATE SEQUENCE person_id_seq;
CREATE SEQUENCE rating_id_seq;

-- успеваемость
CREATE TABLE rating(
                       rating_id INT DEFAULT nextval('rating_id_seq') PRIMARY KEY,
                       physics INT,
                       mathematics INT,
                       rus INT,
                       literature INT,
                       geometry INT,
                       informatics INT
);

-- ученики
CREATE TABLE person(
    person_id INT DEFAULT nextval('person_id_seq') PRIMARY KEY ,
    family VARCHAR(20),
    name VARCHAR(20),
    age INT,
    "group" INT,
    rating_id INT,
    FOREIGN KEY (rating_id) REFERENCES rating(rating_id)
);

-- список групп
CREATE TABLE "group"
(
    group_id INT PRIMARY KEY ,
    group_value INT
);

-- список предметов
CREATE TABLE syllabus(
    academic_object_id INT PRIMARY KEY ,
    academic_object_value VARCHAR(20)
);
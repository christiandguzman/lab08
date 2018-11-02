DROP DATABASE if exists NoteDB;
CREATE DATABASE NoteDB;

USE NoteDB;


CREATE TABLE users (
    username VARCHAR(20) NOT NULL, 
    password VARCHAR(20) NOT NULL, 
    firstname VARCHAR(20), 
    lastname VARCHAR(20), 
    email VARCHAR(40), 
    CONSTRAINT PK_username PRIMARY KEY (username));

CREATE TABLE note (
    noteid INT NOT NULL AUTO_INCREMENT, 
    datecreated DATE NOT NULL, 
    contents VARCHAR(10000) NOT NULL, 
    CONSTRAINT PK_noteId PRIMARY KEY (noteId));



INSERT INTO users(username, password)
VALUES("admin","password");

INSERT INTO note (datecreated,contents)
VALUES (current_date,"example note");

INSERT INTO note (datecreated,contents)
VALUES (current_date,"example note");

INSERT INTO note (datecreated,contents)
VALUES (current_date,"example note");

INSERT INTO note (datecreated,contents)
VALUES (current_date,"example note");

INSERT INTO note (datecreated,contents)
VALUES (current_date,"example note");


COMMIT;

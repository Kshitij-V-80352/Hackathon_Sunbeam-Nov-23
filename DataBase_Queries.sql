create database bookMyShow;
use bookMyShow;

CREATE TABLE movies (
  movie_id INT AUTO_INCREMENT PRIMARY KEY ,
  title VARCHAR(40) NOT NULL,
  released DATE NOT NULL
);

CREATE TABLE users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(40) NOT NULL,
  last_name VARCHAR(40) NOT NULL,
  email VARCHAR(40) NOT NULL UNIQUE,
  password VARCHAR(20) NOT NULL,
  mobile VARCHAR(10) NOT NULL,
  birth DATE NOT NULL
);

CREATE TABLE reviews (
  review_id INT AUTO_INCREMENT PRIMARY KEY ,
  movie_id INT NOT NULL,
  review VARCHAR(240) NOT NULL,
  rating INT NOT NULL,
  user_id INT NOT NULL ,
  modified TIMESTAMP NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE shares(
share_id INT AUTO_INCREMENT PRIMARY KEY,
review_id INT NOT NULL,
user_id INT NOT NULL,
FOREIGN KEY (review_id) REFERENCES reviews(review_id) ON DELETE CASCADE,
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO users (first_name, last_name, email, password, mobile, birth)
VALUES ("sherya", "shinde", "sheryashinde@gmail.com", "Sherya@123", "9876543210", "2003-06-22");

INSERT INTO users (first_name, last_name, email, password, mobile, birth)
VALUES ("ritu","patil","ritupatil@gmail.com","Ritu@123","7894561231","2000-01-02");

INSERT INTO users (first_name, last_name, email, password, mobile, birth)
VALUES ("abhi","patil","abhipatil@gmail.com","Abhi@123","9999999999","2001-01-02");

INSERT INTO users (first_name, last_name, email, password, mobile, birth)
VALUES ("manoj","hare","manojhare@gmail.com","Manoj@123","8787878787","1998-01-02");

INSERT INTO users (first_name, last_name, email, password, mobile, birth)
VALUES ("shweta","sharma","shwetasharma@gmail.com","Shweta@123","7545152535","2001-01-02");

INSERT INTO users (first_name, last_name, email, password, mobile, birth)
VALUES ("payal","pande","payalpande@gmail.com","Payal@123","7474757576","1998-05-02");

INSERT INTO users (first_name, last_name, email, password, mobile, birth)
VALUES ("amol","mane","amolmane@gmail.com","Amol@123","8554266231","1998-11-15");

INSERT INTO users (first_name, last_name, email, password, mobile, birth)
VALUES  ("amreen","merchant","amreenmerchant@gmail.com","Amreen@123","7574727141","1997-11-15");

INSERT INTO users (first_name, last_name, email, password, mobile, birth)
VALUES  ("aishwarya","bagal","aishwaryabagal@gmail.com","Aishwarya@123","6565454512","1997-05-15");
  
INSERT INTO users (first_name, last_name, email, password, mobile, birth)
VALUES  ("neha","hole","nehahole@gmail.com","Neha@123","8525124565","1995-11-15");

INSERT INTO movies (title, released)
VALUES ('Dilwale Dulhania Le Jayenge', '1995-10-20');

INSERT INTO movies (title, released)
VALUES ('3 Idiots', '2009-12-25');

INSERT INTO movies (title, released)
VALUES ('Lagaan', '2001-06-15');

INSERT INTO movies (title, released)
VALUES ('Baahubali: The Beginning', '2015-07-10');

INSERT INTO movies (title, released)
VALUES ('Kabir Singh', '2017-08-25');

INSERT INTO movies (title, released)
VALUES ('Maharshi', '2019-05-09');

INSERT INTO movies (title, released)
VALUES ('Ala Vaikunthapurramuloo', '2020-01-12');

INSERT INTO movies (title, released)
VALUES ('Sarrainodu', '2016-04-22');

INSERT INTO movies (title, released)
VALUES ('Master', '2021-01-13');

INSERT INTO movies (title, released)
VALUES ('Baahubali 2: The Conclusion', '2017-04-28');

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(1,"Best Love Story",7,1,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(2,"best movie",7,1,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(3,"Best cricket match",7,1,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(1,"Engineers life ",9,2,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(2,"Describe Engineering Life!",10,2,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(2,"Do or Die",10,3,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(3,"Dont Lose Hopes",9,3,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(4,"Best Action movie with some suspense ",10,4,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(1,"Love Story",10,4,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(4,"Best Action movie with some suspense ",10,4,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(10,"Best Action movie ",10,4,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(9," vaathi coming !!",10,4,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(5,"Love is Blind!!",8,5,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(6,"Motivation for increase the confidence",9,6,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(7,"Best action movie",10,7,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(8,"Best action movie",8,8,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(9,"Teacher becomes hero",10,9,CURRENT_TIMESTAMP());

INSERT into reviews(movie_id,review,rating,user_id,modified)
VALUES(10,"Best action movie",8,10,CURRENT_TIMESTAMP());

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,1,5);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,1,6);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,1,2);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,1,3);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,1,5);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,2,1);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,3,10);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,3,9);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,4,2);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,5,4);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,6,8);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,7,1);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,8,1);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,8,6);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,9,5);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,9,7);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,10,8);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,10,3);

INSERT into shares(share_id , review_id , user_id)
VALUES(default ,9,8);

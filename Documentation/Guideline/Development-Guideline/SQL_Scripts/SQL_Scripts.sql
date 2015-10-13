CREATE DATABASE brea;
USE brea;
CREATE TABLE roles(
	id int(11) NOT NULL AUTO_INCREMENT,
	role varchar(45) NOT NULL,
	description varchar(100),
	PRIMARY KEY (id)
);

CREATE  TABLE users (
	id int(11) NOT NULL AUTO_INCREMENT,
	username VARCHAR(45) NOT NULL ,
	password VARCHAR(60) NOT NULL ,
	enabled TINYINT NOT NULL DEFAULT 1 ,
	role_id int(11) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `fk_user_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `users_info` (
	`user_id` int(11) NOT NULL,
	`user_first_name` varchar(45) DEFAULT NULL,
	`user_last_name` varchar(45) DEFAULT NULL,
	`user_email` varchar(255) DEFAULT NULL,
	`user_phone` varchar(45) DEFAULT NULL,
	`user_address` varchar(255) DEFAULT NULL,
	PRIMARY KEY (`user_id`),
	UNIQUE KEY `fk_users_info_1` (`user_id`),
	CONSTRAINT `fk_users_info_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
);

INSERT INTO roles(role,description)
VALUES ('ROLE_ADMIN','admin');

INSERT INTO roles(role,description)
VALUES ('ROLE_USER','user');

INSERT INTO users(username,password, enabled,role_id)
VALUES ('admin','$2a$10$6tF50m7cvFt5oWJhoiQhF.C559./Fpvj.bGswovKfJFdXy9q0TDi6',true,1);

INSERT INTO users(username,password, enabled,role_id)
VALUES ('user','$2a$10$AnCxPjeDV36tT.2dOjvfJO54h7xaUY9s7Ac8.FwmFM3L.uhdAwIQ6',true,2);

INSERT INTO users_info(user_id,user_first_name,user_last_name,user_email,user_phone,user_address)
VALUES (1,'admin','nguyen','admin@admin.com',"123-123-1234","da nang");

INSERT INTO users_info(user_id,user_first_name,user_last_name,user_email,user_phone,user_address)
VALUES (2,'user','nguyen','user@user.com',"123-123-1235","da nang user");








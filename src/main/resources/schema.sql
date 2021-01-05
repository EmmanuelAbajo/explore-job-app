CREATE TABLE freelancer (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	experience_level VARCHAR(20) NOT NULL
);

CREATE TABLE job_scope (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(2000) NOT NULL,
	renumeration INT NOT NULL,
	freelancer_id BIGINT
);

ALTER TABLE job_scope ADD FOREIGN KEY (freelancer_id) REFERENCES freelancer(id);

CREATE TABLE job_scope_rating (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_scope_id BIGINT,
    client_id BIGINT,
    score INT,
    comment VARCHAR(200)
 );
 
 ALTER TABLE job_scope_rating ADD FOREIGN KEY (job_scope_id) REFERENCES job_scope(id);
 ALTER TABLE job_scope_rating ADD UNIQUE MyConstraint (job_scope_id, client_id);
 
 CREATE TABLE security_role (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  description varchar(100) DEFAULT NULL,
  role_name varchar(100) DEFAULT NULL
);


CREATE TABLE security_user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL
);


CREATE TABLE user_role (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  CONSTRAINT FK_SECURITY_USER_ID FOREIGN KEY (user_id) REFERENCES security_user (id),
  CONSTRAINT FK_SECURITY_ROLE_ID FOREIGN KEY (role_id) REFERENCES security_role (id)
);


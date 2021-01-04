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

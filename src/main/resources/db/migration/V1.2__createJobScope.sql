CREATE TABLE job_scope (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(2000) NOT NULL,
	renumeration INT NOT NULL,
	freelancer_id BIGINT
);

ALTER TABLE job_scope ADD FOREIGN KEY (freelancer_id) REFERENCES freelancer(id);
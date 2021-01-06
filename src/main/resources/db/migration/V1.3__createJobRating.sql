CREATE TABLE job_scope_rating (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_scope_id BIGINT,
    client_id BIGINT,
    score INT,
    comment VARCHAR(200)
 );
 
 ALTER TABLE job_scope_rating ADD FOREIGN KEY (job_scope_id) REFERENCES job_scope(id);
 ALTER TABLE job_scope_rating ADD UNIQUE MyConstraint (job_scope_id, client_id);
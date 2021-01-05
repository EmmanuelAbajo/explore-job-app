INSERT INTO freelancer (first_name,last_name,experience_level) VALUES ('Jack', 'Don', 'INTERMEDIATE');
INSERT INTO freelancer (first_name,last_name,experience_level) VALUES ('Richard', 'Hendricks', 'EXPERT');
INSERT INTO freelancer (first_name,last_name,experience_level) VALUES ('Billy', 'biggeti', 'BEGINNER');

INSERT INTO job_scope (name, description, renumeration, freelancer_id) VALUES ('Law firm website', 'Build a law firm website on the MEAN stack', 500, null);
INSERT INTO job_scope (name, description, renumeration, freelancer_id) VALUES ('Netflix rebuild', 'Migrate netflix''s architecture to micro services based', 2000, null);
INSERT INTO job_scope (name, description, renumeration, freelancer_id) VALUES ('Cloud based implementation', 'Migrate paystack''s payment architecture to cloud', 1500, null);

INSERT INTO job_scope_rating ( job_scope_id, client_id, score,comment) VALUES (1,50,5, 'Looks good');
INSERT INTO job_scope_rating ( job_scope_id, client_id, score,comment) VALUES (1,51,3, 'average');
INSERT INTO job_scope_rating ( job_scope_id, client_id, score,comment) VALUES (2,50,2, 'No comment');
INSERT INTO job_scope_rating ( job_scope_id, client_id, score,comment) VALUES (2,53,1, 'Scope not clear');
INSERT INTO job_scope_rating ( job_scope_id, client_id, score,comment) VALUES (3,53,4, 'NO comment');
INSERT INTO job_scope_rating ( job_scope_id, client_id, score,comment) VALUES (3,50,2, 'No comment');
# Job Scope App 
Test application with domains: Job scope, freelancer and job scope rating

Features include:
- Logging
- Unit tests
- Integration tests
- API Documentation with swagger
- JWT implementation
- Database initialization and seeding
- Database migration
- Containerization with docker

## Setup
To build the image, run `docker build -t <image-name> .`

To spin up a container from the image based on the h2 in-memory db, run `docker run --name <container-name> -p <port>:8080 -d <imagename:tag>`

Alternatively you can pull the container from my dockerhub repository using `docker run --name <container-name> -p <port>:8080 -d emmanuelabajo/explore-jobs:latest`

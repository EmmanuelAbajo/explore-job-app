FROM openjdk:11
WORKDIR /
ADD target/explore-0.0.1-SNAPSHOT.jar //
EXPOSE 8080
ENTRYPOINT [ "java", "-Dspring.profiles.active=mysql", "-jar", "/explore-0.0.1-SNAPSHOT.jar"]

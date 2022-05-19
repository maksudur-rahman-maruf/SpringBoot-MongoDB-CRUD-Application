## FROM openjdk:8-jdk-alpine
FROM openjdk:8
LABEL maintainer="maruf@javatogmail.com"
VOLUME /main-app
ADD target/springboot-mongo-docker.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]
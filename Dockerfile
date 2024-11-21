#FROM ubuntu:latest
#LABEL authors="Administrator"
#
#ENTRYPOINT ["top", "-b"]

# Use an appropriate base image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/spring-boot-application.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

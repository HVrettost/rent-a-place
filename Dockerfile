FROM dvmarques/openjdk-14-jdk-alpine-with-timezone:latest
ARG JAR_FILE=/application/build/libs/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]
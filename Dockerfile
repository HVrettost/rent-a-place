FROM openjdk:17.0.1-jdk-oraclelinux7
ARG JAR_FILE=../application/build/libs/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]
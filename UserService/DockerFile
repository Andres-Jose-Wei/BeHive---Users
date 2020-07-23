FROM openjdk:8-jdk-alpine
ADD target/*.jar UserService-0.0.1-SNAPSHOT.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "UserService-0.0.1-SNAPSHOT.jar"]
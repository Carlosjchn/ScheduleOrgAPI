FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/jpademo-0.0.1-SNAPSHOT.war /app/testapp.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/testapp.war"]

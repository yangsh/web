FROM openjdk:8-jre-alpine
ADD ./target/web-1.0-SNAPSHOT.jar /app.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app.jar"]
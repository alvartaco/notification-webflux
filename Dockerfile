FROM openjdk:21
EXPOSE 8080
ADD target/notifications-0.0.1-SNAPSHOT.jar notifications-app.jar
ENTRYPOINT ["java", "-jar", "notifications-app.jar"]

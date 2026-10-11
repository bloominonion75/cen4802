FROM eclipse-temurin:20-jre

WORKDIR /app

COPY target/task-manager-1.0.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-cp", "app.jar", "main.java.ClassTaskManager"]
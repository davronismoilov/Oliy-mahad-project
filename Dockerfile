FROM openjdk:11

COPY target/oliymahad-1.1.0.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app.jar"]


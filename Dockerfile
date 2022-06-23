FROM openjdk:11

COPY target/oliymahad.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app.jar"]


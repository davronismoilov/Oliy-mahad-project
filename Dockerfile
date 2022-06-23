FROM openjdk:11

COPY target/oliymahad.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app.jar"]


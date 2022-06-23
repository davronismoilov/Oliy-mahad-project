FROM openjdk:11

COPY target/oliymahad.jar app-test.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app-test.jar"]


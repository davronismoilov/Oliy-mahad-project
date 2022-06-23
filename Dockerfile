FROM openjdk:11

COPY target/oliymahad.jar app1.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app1.jar"]

FROM openjdk:11

COPY target/eureka-server.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app.jar"]


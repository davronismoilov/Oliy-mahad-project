FROM openjdk:17

COPY target/oliymahad.jar oliymahad-app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/oliymahad-app.jar"]

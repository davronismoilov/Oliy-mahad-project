FROM openjdk:11

COPY target/oliy-mahad.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app.jar"]


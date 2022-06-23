FROM openjdk:17

COPY target/oliy-mahad.jar app-o.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app-0.jar"]


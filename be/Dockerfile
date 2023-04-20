FROM adoptopenjdk/openjdk11
CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE=build/libs/asap-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} asap.jar
ENTRYPOINT ["java", "-jar", "/asap.jar", "--spring.profiles.active=default"]

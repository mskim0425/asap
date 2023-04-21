FROM adoptopenjdk/openjdk11
CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE=./be/build/libs/be-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} be.jar
ENTRYPOINT ["java", "-jar", "/be.jar", "--spring.profiles.active=default"]

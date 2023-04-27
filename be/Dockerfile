FROM adoptopenjdk/openjdk11
CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE=./be/build/libs/be-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} be.jar
ENV AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID}
ENV AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY}
ENTRYPOINT ["java", "-jar", "/be.jar", "--spring.profiles.active=default"]

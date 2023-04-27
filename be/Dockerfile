FROM adoptopenjdk/openjdk11

# jenkins build 환경변수를 build-arg 로 전달
ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY

# 환경변수를 활용하여 실행
CMD ["./mvnw", "clean", "package"]
ADD ./be/build/libs/be-0.0.1-SNAPSHOT.jar be.jar

ENTRYPOINT ["java", "-jar", "/be.jar", "--spring.profiles.active=default", "--aws.access-key-id=${AWS_ACCESS_KEY_ID}", "--aws.secret-access-key=${AWS_SECRET_ACCESS_KEY}"]

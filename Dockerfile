FROM gradle:7.4.1-jdk11-alpine as builder
ENV BUILD_HOME=/app
WORKDIR $BUILD_HOME
COPY src ./src
COPY build.gradle.kts settings.gradle.kts ./
RUN gradle clean build -x test

FROM openjdk:11-jre-slim
ENV APP_HOME=/app
ENV ARTIFACT_NAME=config-server-0.0.1-SNAPSHOT.jar
WORKDIR $APP_HOME
COPY --from=builder /app/build/libs/$ARTIFACT_NAME .
EXPOSE 8888
CMD java -jar ./$ARTIFACT_NAME

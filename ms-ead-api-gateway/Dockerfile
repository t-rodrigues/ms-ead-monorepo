FROM gradle:7.4.1-jdk17-alpine as builder
ENV BUILD_HOME=/build
WORKDIR $BUILD_HOME
COPY src ./src
COPY build.gradle.kts settings.gradle.kts ./
RUN gradle clean build -x test

FROM openjdk:17-jdk-slim
ENV APP_HOME=/app
ENV ARTIFACT_NAME=api-gateway-0.0.1-SNAPSHOT.jar
WORKDIR $APP_HOME
COPY --from=builder /build/build/libs/$ARTIFACT_NAME .
EXPOSE 8080
CMD java -jar ./$ARTIFACT_NAME

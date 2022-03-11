FROM gradle:7.4.1-jdk17-alpine as builder
ENV BUILD_HOME=/app
WORKDIR $BUILD_HOME
COPY src $BUILD_HOME/src
COPY build.gradle.kts settings.gradle.kts $BUILD_HOME/
RUN gradle clean build -x test

FROM openjdk:17-jdk-slim
ENV APP_HOME=/app
ENV ARTIFACT_NAME=service-register-0.0.1-SNAPSHOT.jar
WORKDIR $APP_HOME
COPY --from=builder /app/build/libs/$ARTIFACT_NAME $APP_HOME
EXPOSE 8761
CMD java -jar /$APP_HOME/$ARTIFACT_NAME

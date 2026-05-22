FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build/libs/ms-user.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","app.jar"]
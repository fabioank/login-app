# Estágio de build
FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-17-jdk maven
WORKDIR /app
COPY . .
RUN mvn clean install

# Estágio de produção
FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /app/target/loginapp-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
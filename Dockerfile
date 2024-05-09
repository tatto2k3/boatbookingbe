FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DshipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/boatbookingbe-0.0.1-SNAPSHOT.jar boatbookingbe.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "boatbookingbe.jar"]
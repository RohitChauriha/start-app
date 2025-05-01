#FROM maven:3.8.3-openjdk-17 AS builder
#COPY . /app
#WORKDIR /app
#RUN mvn clean install -e

#FROM openjdk:17 AS runtime
#WORKDIR /app
#COPY --from=builder /app/target/back-end-demo-0.0.1-SNAPSHOT.jar /app/back-end.jar
#ENTRYPOINT ["java", "-jar", "/app/back-end.jar"]

FROM openjdk:17 AS runtime
COPY ./target/back-end-demo-0.0.1-SNAPSHOT.jar /app/back-end.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/back-end.jar"]
# ---- Build stage ----
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy pom and resolve dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B -Pprod

# Copy source
COPY src ./src

# Build production artifact (no docker-compose dep)
RUN mvn clean package -DskipTests -Pprod

# ---- Runtime stage ----
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8081

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ENTRYPOINT ["java","-jar","app.jar"]

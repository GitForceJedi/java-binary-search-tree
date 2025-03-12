# Use Gradle image to build the project
FROM gradle:7.6.0-jdk11 AS build

WORKDIR /app

# Copy project files
COPY . .

# Ensure Gradle Wrapper has execute permissions
RUN chmod +x ./gradlew

# Build the project using Gradle
RUN ./gradlew clean build

# Use a lightweight OpenJDK image for the final container
FROM openjdk:11-jdk-slim

WORKDIR /app

# Install necessary dependencies
RUN apt-get update && apt-get install -y curl && \
    curl -LO https://github.com/tsl0922/ttyd/releases/download/1.7.2/ttyd.x86_64 && \
    chmod +x ttyd.x86_64 && mv ttyd.x86_64 /usr/local/bin/ttyd

# Copy the built JAR from the previous stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port for ttyd
EXPOSE 8181

# Run ttyd to serve the terminal with the Java app
CMD ["ttyd", "-p", "8181", "java", "-jar", "app.jar"]

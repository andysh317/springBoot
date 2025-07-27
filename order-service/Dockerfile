# Use a lightweight Java image
FROM openjdk:17-jdk-slim

# Add a label
LABEL maintainer="andysh317@gmail.com"

# Set working directory inside the container
WORKDIR /app

# Copy the jar file
COPY target/order-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port (same as in application.properties)
EXPOSE 8082

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
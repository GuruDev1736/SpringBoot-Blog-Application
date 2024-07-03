# Use an appropriate base image with Java 21
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and project files
COPY . .

# Ensure the Maven wrapper is executable
RUN chmod +x ./mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "target/Blog-Application-0.0.1-SNAPSHOT.jar"]

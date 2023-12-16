FROM eclipse-temurin:17-jdk-jammy

# Set working directory in the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml file
COPY ./mvnw .
COPY ./.mvn/ .mvn/
COPY ./pom.xml .

# Download project dependencies
RUN ./mvnw dependency:resolve

# Copy the project source
COPY ./src/ src/

# Build the application
CMD ["./mvnw", "spring-boot:run"]

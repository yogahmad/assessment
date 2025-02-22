FROM --platform=linux/amd64 gradle:8.5-jdk21 as build
WORKDIR /app

# Copy only the build files first
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Create .gradle directory and set permissions
RUN mkdir -p /home/gradle/.gradle && \
    chown -R gradle:gradle /home/gradle/.gradle

# Download dependencies first (with better caching)
RUN --mount=type=cache,target=/home/gradle/.gradle \
    gradle downloadDependencies --no-daemon --stacktrace

# Then copy source and build
COPY src ./src
RUN --mount=type=cache,target=/home/gradle/.gradle \
    gradle clean build --no-daemon --stacktrace

FROM --platform=linux/amd64 eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
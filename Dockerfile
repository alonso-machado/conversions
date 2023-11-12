# Compile and package the application to an executable JAR
FROM maven:3.6.3 AS maven
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package

# Execute the JAR on jre-alpine for less image size (Need to check if it runs smooth or use non-alpine for compatibility with glibc)
FROM eclipse-temurin:17-jre-alpine
RUN mkdir /opt/app
ARG JAR_FILE=*.jar
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app
EXPOSE 8080
CMD ["java", "-Dserver.port=8080", "-jar", "/opt/app/*.jar"]